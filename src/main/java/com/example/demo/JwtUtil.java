package com.example.demo;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.security.Key;

@Component
public class JwtUtil {

    private static final Key KEY = Keys.hmacShaKeyFor(
            "mysecretkeymysecretkeymysecretkey12345"
                    .getBytes(StandardCharsets.UTF_8)
    );

    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public boolean validateToken(String token) {

        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}