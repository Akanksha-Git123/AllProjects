package com.payingguest.app.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtUtils {

    private static final long EXPIRATION_TIME_IN_MILLIS = 1000L * 60 * 60 * 24 * 30 * 6; // 6 months
    private SecretKey key;

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // 🔐 Generate token with email and userType
    public String generateToken(String email, String userType) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME_IN_MILLIS);

        return Jwts.builder()
                .subject(email)
                .claim("userType", userType)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    // ✅ Extract email (subject)
    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // ✅ Extract userType from token
    public String getUserTypeFromToken(String token) {
        return extractClaims(token, claims -> claims.get("userType", String.class));
    }

    // ✅ Validate token with UserDetails
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // 🕒 Check expiration
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaims(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    // 🧠 Extract claims generically
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }
}
