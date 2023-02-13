package com.server.stakantoserver.service;

import com.server.stakantoserver.controller.dto.request.SignUpRequest;
import com.server.stakantoserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public void signUp(SignUpRequest signUpRequest) {
        userRepository.save(signUpRequest.toUser());
    }
}
