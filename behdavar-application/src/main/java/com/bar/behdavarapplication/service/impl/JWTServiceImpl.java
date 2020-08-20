package com.bar.behdavarapplication.service.impl;

import com.bar.behdavarapplication.configuration.JWTProperties;
import com.bar.behdavarapplication.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    public final JWTProperties properties;


    public JWTServiceImpl(JWTProperties properties) {
        this.properties = properties;
    }


    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiry() * 60_000))
                .signWith(getAlgorithm(properties.getSignatureAlgorithmName()), properties.getSecret())
                .compact();
    }


    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SignatureAlgorithm getAlgorithm(String name) {
        try {
            return SignatureAlgorithm.forName(name);
        } catch (SignatureException e) {
            return SignatureAlgorithm.HS256;
        }

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


}
