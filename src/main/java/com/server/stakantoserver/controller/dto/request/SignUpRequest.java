package com.server.stakantoserver.controller.dto.request;

import com.server.stakantoserver.entity.User;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String name;

    private String image;

    private String password;

    private String accountID;

    public User toUser() {
        return User.builder()
                .accountID(this.accountID)
                .image(this.image)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
