package com.server.stakantoserver.controller.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {

    private String accountID;

    private String password;

}
