package com.bar.behdavarapplication.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    boolean validateToken(String token, UserDetails userDetails) throws JwtException;

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws JwtException;

    String extractUsername(String token) throws JwtException;

    Date extractExpiration(String token) throws JwtException;
}
