package com.server.stakantoserver.controller.dto.request;

import com.server.stakantoserver.entity.User;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class SignUpRequest {

    private String name;

    private String image;

    private String password;

    private String accountID;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .accountID(this.accountID)
                .image(this.image)
                .name(this.name)
                .password(passwordEncoder.encode(this.password))
                .build();
    }
}
