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
        if (userRepository.findByAccountID(signUpRequest.getAccountID())
                .isPresent()) {
            throw new RuntimeException("the account id is already in Database");
        }
        userRepository.save(signUpRequest.toUser());
    }
}
