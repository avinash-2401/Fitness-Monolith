package com.project.Fitness.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private final String jwtToken =
            "YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmcK";

    private final long expiryTimeMs = 172800000;

    public String generateJwtToken(String userId, String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(new java.util.Date())
                .expiration(new java.util.Date(System.currentTimeMillis() + expiryTimeMs))
                .signWith(key())
                .compact();
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtToken));
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        } catch (Exception e) {
            // optional: log e.getMessage()
            return false;
        }
    }

    public String getUserID(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String jwt) {
        try {
            var payload = Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

            Object rolesObj = payload.get("roles");
            if (rolesObj instanceof List) {
                return ((List<?>) rolesObj).stream()
                        .map(Object::toString)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            // ignore parse errors: return empty list
        }
        return Collections.emptyList();
    }
}
