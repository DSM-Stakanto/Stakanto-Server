package com.server.stakantoserver.security;

import com.server.stakantoserver.security.details.UserDetails;
import com.server.stakantoserver.security.details.UserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expired.access}")
    private Long accessExp;

    @Value("${jwt.expired.refresh}")
    private Long refreshExp;

    private final UserDetailsService userDetailsService;

    private byte[] encodingKey() {
        return Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8)).getBytes();
    }

    public Authentication authentication(String token) {
        Claims claims = tokenParser(token);
        UserDetails auth = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(auth, null, auth.getAuthorities());
    }

    public Claims tokenParser(String token) {
        return Jwts.parserBuilder().setSigningKey(encodingKey()).build().parseClaimsJws(token).getBody();
    }
}
