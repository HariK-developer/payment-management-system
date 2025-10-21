package com.smartpay.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey secretKey;
    private final Dotenv dotenv = Dotenv.load();

    @PostConstruct
    public void init(){
        String secret = dotenv.get("JWT_SECRET");
        if (secret == null || secret.isBlank()){
            throw new IllegalStateException("JWT_SECRET not found in .env file");
        }

        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private static final long EXPIRATION_TIME = 1000*60*60*24;

    public String generateToken(String username){

        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (JwtException e) {
            return false;
        }
        return true;
    }
}