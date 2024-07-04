package com.example.stdManagement.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.educ.entity.School;
import com.example.stdManagement.entity.Admin;
import com.example.stdManagement.entity.Student;
import com.example.stdManagement.entity.Teacher;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(Object user) {
        return Jwts.builder()
                .setSubject(getEmail(user))
                .claim("role", getRole(user))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, Object user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .claim("role", getRole(user))
                .setSubject(getEmail(user))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public String extractUserRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token, Object user) {
        final String username = extractUserName(token);
        return username.equals(getEmail(user)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private String getEmail(Object user) {
        if (user instanceof UserDetails) {
            return ((UserDetails) user).getUsername();
        } else if (user instanceof Student) {
            return ((Student) user).getEmail();
        } else if (user instanceof Teacher) {
            return ((Teacher) user).getEmail();
        } else if (user instanceof Admin) {
            return ((Admin) user).getEmail();
        } else if (user instanceof School) {
            return ((School) user).getEmail();
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }
    }

    private String getRole(Object user) {
        if (user instanceof Student) {
            return "Student";
        } else if (user instanceof Teacher) {
            return "Teacher";
        } else if (user instanceof Admin) {
            return "Admin";
        } else if (user instanceof School) {
            return "School";
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }
    }
}
