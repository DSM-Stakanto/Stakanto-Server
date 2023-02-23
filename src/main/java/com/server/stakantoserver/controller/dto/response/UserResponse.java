package com.server.stakantoserver.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {
    private final String image;
    private final String name;

    @Builder
    public UserResponse(String image, String name) {
        this.image = image;
        this.name = name;
    }
}
