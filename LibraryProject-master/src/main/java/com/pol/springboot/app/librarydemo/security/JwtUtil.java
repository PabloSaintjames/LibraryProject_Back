package com.pol.springboot.app.librarydemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMillis;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationMillis
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMillis = expirationMillis;
    }

    /**
     * Genera un JWT con:
     * - subject  -> email
     * - uid      -> id del usuario (PK)
     * - rol      -> rol principal
     */
    public String generarToken(Long userId, String email, String rol) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(email)
                .claim("uid", userId)
                .claim("rol", rol)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Devuelve todos los claims del token.
     * Valida firma y expiración automáticamente.
     */
    public Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Devuelve el email (subject)
     */
    public String getEmail(String token) {
        return extraerClaims(token).getSubject();
    }

    /**
     * Devuelve el ID de usuario (uid)
     */
    public Long getUserId(String token) {
        return extraerClaims(token).get("uid", Long.class);
    }

    /**
     * Devuelve el rol
     */
    public String getRol(String token) {
        return extraerClaims(token).get("rol", String.class);
    }

    /**
     * Valida el token (firma + expiración)
     */
    public boolean esTokenValido(String token) {
        try {
            extraerClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
