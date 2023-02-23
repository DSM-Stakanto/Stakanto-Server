package com.server.stakantoserver.controller.dto.request;

import com.server.stakantoserver.entity.Hint;
import lombok.Getter;

@Getter
public class MusicInfoRequest {

    private String answer;

    private String code;

    private String name;

    private int startAt;

    private Hint hint;

    private Genre genre;
}
