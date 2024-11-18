package com.auth.jwt.security;

import com.auth.jwt.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.*;

@Component
public class JwtProvider {

    /*@Value("${jwt.secret}")
    private String secret;*/

    private Key secret;

    @PostConstruct
    protected void init() {
        byte[] apiKeySecretBytes = new byte[64];
        new SecureRandom().nextBytes(apiKeySecretBytes);
        secret = Keys.hmacShaKeyFor(apiKeySecretBytes);
    }

    public String createToken(AuthUser authUser){

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authUser.getId());
        //claims.put("role")

        // Fecha de emision y expiracion del token
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .claims(claims)
                .subject(authUser.getUserName())
                .issuedAt(now)
                .expiration(exp)
                .signWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .compact();

    }

    // Valida que el token sea correcto
    public boolean validate(String token) {
        try {

            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // Obtiene el UserName desde el token jwt
    public String getUserNameFromToken(String token){
        try {

            return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();

        } catch (Exception e) {
            return null;
        }
    }
}
