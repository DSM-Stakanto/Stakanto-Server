package com.server.stakantoserver.controller.dto.response.findRank;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TopRankResponse {
    private final List<Genre> genreList;

    public TopRankResponse(List<Genre> list) {
        this.genreList = list;
    }
}

