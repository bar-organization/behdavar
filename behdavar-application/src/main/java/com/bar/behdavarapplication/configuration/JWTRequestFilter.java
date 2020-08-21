package com.bar.behdavarapplication.configuration;

import com.bar.behdavarapplication.service.AppUserDetailService;
import com.bar.behdavarapplication.service.JWTService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final AppUserDetailService userDetailsService;
    private final JWTService jwtService;
    private final HandlerExceptionResolver resolver;

    public JWTRequestFilter(AppUserDetailService userDetailsService, JWTService jwtService, @Qualifier("errorAttributes") HandlerExceptionResolver resolver) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtService.extractUsername(jwt);
            } catch (JwtException e) {
                resolver.resolveException(request, response, null, e);
            }

        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            boolean isValidateToken = false;
            try {
                isValidateToken = jwtService.validateToken(jwt, userDetails);
            } catch (JwtException e) {
                resolver.resolveException(request, response, null, e);
            }
            if (isValidateToken) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
