package com.bar.behdavarapplication.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    boolean validateToken(String token, UserDetails userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractUsername(String token);

    Date extractExpiration(String token);
}
