package com.server.stakantoserver.controller.dto.response.findRank;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Genre {

    private final String name;

    private final int score;

    private final String genre;

    @Builder
    public Genre(String name, int score, String genre) {
        this.name = name;
        this.score = score;
        this.genre = genre;
    }
}
