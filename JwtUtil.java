package com.example.jwtgeneration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey KEY =
        Keys.hmacShaKeyFor(
            "MySecretKeyForJWTGeneration123456789"
                .getBytes()
        );

    public static String generateToken(
            String username, String role) {

        return Jwts.builder()
            .subject(username)
            .claim("role", role)
            .issuedAt(new Date())
            .expiration(
                new Date(System.currentTimeMillis()
                    + 3600000)
            )
            .signWith(KEY)
            .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException |
                 IllegalArgumentException e) {
            return false;
        }
    }
}