package com.server.stakantoserver.controller;

import com.server.stakantoserver.controller.dto.request.SignInRequest;
import com.server.stakantoserver.controller.dto.request.SignUpRequest;
import com.server.stakantoserver.controller.dto.response.TokenResponse;
import com.server.stakantoserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }
}
