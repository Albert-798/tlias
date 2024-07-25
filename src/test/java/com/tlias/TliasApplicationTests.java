package com.tlias;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void genJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "Tom");

        String jwt = Jwts.builder()
                .setClaims(claims) // Custom payload
                .signWith(SignatureAlgorithm.HS256, "itheima") // Signature algorithm
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000)) // Valid for 1 day
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void parseJwt() {
        String jwt = "your.jwt.token.here"; // Replace with the JWT token you want to parse

        try {
            Map<String, Object> claims = Jwts.parser()
                    .setSigningKey("itheima")
                    .parseClaimsJws(jwt)
                    .getBody();

            System.out.println("ID: " + claims.get("id"));
            System.out.println("Username: " + claims.get("username"));
        } catch (ExpiredJwtException e) {
            System.out.println("JWT has expired: " + e.getMessage());
            // Handle the expired token scenario here (e.g., re-authenticate user or refresh token)
        } catch (Exception e) {
            System.out.println("JWT parsing error: " + e.getMessage());
        }
    }

    @Test
    public void parseJwtWithClockSkew() {
        String jwt = "your.jwt.token.here"; // Replace with the JWT token you want to parse

        try {
            Map<String, Object> claims = Jwts.parser()
                    .setSigningKey("itheima")
                    .setAllowedClockSkewSeconds(60) // Allow 60 seconds of clock skew
                    .parseClaimsJws(jwt)
                    .getBody();

            System.out.println("ID: " + claims.get("id"));
            System.out.println("Username: " + claims.get("username"));
        } catch (ExpiredJwtException e) {
            System.out.println("JWT has expired: " + e.getMessage());
            // Handle the expired token scenario here (e.g., re-authenticate user or refresh token)
        } catch (Exception e) {
            System.out.println("JWT parsing error: " + e.getMessage());
        }
    }
}
