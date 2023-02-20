package com.server.stakantoserver.service;

import com.server.stakantoserver.controller.dto.request.SignInRequest;
import com.server.stakantoserver.controller.dto.request.SignUpRequest;
import com.server.stakantoserver.controller.dto.response.TokenResponse;
import com.server.stakantoserver.entity.User;
import com.server.stakantoserver.repository.UserRepository;
import com.server.stakantoserver.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider provider;

    public void signUp(SignUpRequest signUpRequest) {
        if (userRepository.findByAccountID(signUpRequest.getAccountID())
                .isPresent()) {
            throw new RuntimeException("the account id is already in Database");
        }
        userRepository.save(signUpRequest.toUser(passwordEncoder));
    }

    public TokenResponse signIn(SignInRequest request) {
        User user = userRepository.findByAccountID(request.getAccountID())
                .orElseThrow(() -> new RuntimeException("id can't find in database"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("password is incorrect");
        }
        return TokenResponse.builder()
                .accessToken(provider.generateAccessToken(user.getAccountID()))
                .refreshToken(provider.generateRefreshToken(user.getAccountID()))
                .build();
    }
}
