package com.server.stakantoserver.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RecentlyLogResponse {
    private final String genre;
    private final List<Integer> scores;

    @Builder
    public RecentlyLogResponse(String genre, List<Integer> scores) {
        this.genre = genre;
        this.scores = scores;
    }
}
