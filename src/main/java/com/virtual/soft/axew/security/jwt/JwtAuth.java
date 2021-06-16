package com.virtual.soft.axew.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtAuth {

    private static final String HEADER_AUTH = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String makeToken (String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    public boolean isValid (String token) {
        if (token == null) {
            return false;
        }

        String[] tokenHeader = token.split(" ");
        if (tokenHeader.length != 2) {
            return false;
        }
        if (!tokenHeader[0].equals(TOKEN_PREFIX.trim())) {
            return false;
        }

        String hash = tokenHeader[1];
        Claims claims = getClaims(hash);
        if (claims == null) {
            return false;
        }

        Date expiration = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());
        return expiration.after(now);
    }

    private Claims getClaims (String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception exception) {
            return null;
        }
    }

    public String getUserName (String token) {
        Claims claims = getClaims(token);
        return claims == null ? null : claims.getSubject();
    }

    public String getHeaderAuth () {
        return HEADER_AUTH;
    }

    public String getTokenPrefix () {
        return TOKEN_PREFIX;
    }
}
