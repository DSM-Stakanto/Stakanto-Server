package com.server.stakantoserver.controller.dto.response;

import com.server.stakantoserver.controller.dto.request.Genre;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RecentlyLogResponse {
    private final Genre genre;
    private final List<Integer> scores;

    @Builder
    public RecentlyLogResponse(Genre genre, List<Integer> scores) {
        this.genre = genre;
        this.scores = scores;
    }
}
