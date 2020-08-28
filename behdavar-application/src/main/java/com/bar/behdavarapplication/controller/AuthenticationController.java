package com.bar.behdavarapplication.controller;

import com.bar.behdavarapplication.model.AuthenticationRequest;
import com.bar.behdavarapplication.model.AuthenticationResponse;
import com.bar.behdavarapplication.service.AppUserDetailService;
import com.bar.behdavarapplication.service.JWTService;
import com.bar.behdavarbackend.business.api.UserBusiness;
import com.bar.behdavarbackend.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailService userDetailService;
    private final UserBusiness userBusiness;

    private final JWTService jwtService;

    public AuthenticationController(AuthenticationManager authenticationManager, AppUserDetailService userDetailService, UserBusiness userBusiness, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.userBusiness = userBusiness;
        this.jwtService = jwtService;
    }

    @PostMapping({"/token", "/login"})
    public ResponseEntity<AuthenticationResponse> getToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        UserDto userDto = userBusiness.findByUserName(authRequest.getUsername());

        final UserDetails userDetails = userDetailService.loadUserByUserDto(userDto);

        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDto));
    }

    @GetMapping("/info")
    public UserDetails getSecurityInfo() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
