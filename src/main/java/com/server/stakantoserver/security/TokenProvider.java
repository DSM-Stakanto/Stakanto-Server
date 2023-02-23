package com.server.stakantoserver.security;

import com.server.stakantoserver.entity.Refresh;
import com.server.stakantoserver.entity.User;
import com.server.stakantoserver.repository.RefreshRepository;
import com.server.stakantoserver.repository.UserRepository;
import com.server.stakantoserver.security.details.UserDetails;
import com.server.stakantoserver.security.details.UserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

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

    private final RefreshRepository refreshRepository;

    private final UserRepository userRepository;

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

    public String generateAccessToken(String accountId) {
        return generateToken(accountId, accessExp);
    }

    public String generateRefreshToken(String accountId) {
        String token = generateToken(accountId, refreshExp);
        User user = userRepository.findByAccountID(accountId)
                        .orElseThrow(()-> new RuntimeException("account doesn't find in database"));
        if(refreshRepository.findByUser(user).isPresent()) {
            refreshRepository.save(
                    refreshRepository.findByUser(user).get().updateToken(token)
            );
        }
        else refreshRepository.save(Refresh.builder()
                        .user(user)
                        .refreshToken(token)
                .build());
        return token;
    }

    private String generateToken(String accountId, Long expired) {
        return Jwts.builder()
                .setSubject(accountId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expired * 1000))
                .signWith(SignatureAlgorithm.HS256, encodingKey())
                .claim("auth", expired.equals(accessExp) ? "access_token" : "refresh_token")
                .compact();
    }
}
