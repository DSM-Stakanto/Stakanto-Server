package com.server.stakantoserver.service;

import com.server.stakantoserver.controller.dto.request.SignInRequest;
import com.server.stakantoserver.controller.dto.request.SignUpRequest;
import com.server.stakantoserver.controller.dto.response.TokenResponse;
import com.server.stakantoserver.entity.User;
import com.server.stakantoserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest signUpRequest) {
        if (userRepository.findByAccountID(signUpRequest.getAccountID())
                .isPresent()) {
            throw new RuntimeException("the account id is already in Database");
        }
        userRepository.save(signUpRequest.toUser(passwordEncoder));
    }

    public TokenResponse signIn(SignInRequest request) {
        User user = userRepository.findByAccountID(request.getAccountID())
                .orElseThrow(RuntimeException::new);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("password is incorrect");
        }
        return TokenResponse.builder()
                .accessToken("")
                .refreshToken("")
                .build();
    }
}
