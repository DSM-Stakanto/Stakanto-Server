package com.server.stakantoserver.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TopRankResponse {
    private final KPop kPop;
    private final Pop pop;
    private final JPop jPop;
    private final Game game;

    @Builder
    public TopRankResponse(KPop kPop, Pop pop, JPop jPop, Game game) {
        this.kPop = kPop;
        this.pop = pop;
        this.jPop = jPop;
        this.game = game;
    }
}

class Genre {
    protected String name;
    protected int score;
}

@Builder
@AllArgsConstructor
class KPop extends Genre{}

@Builder
@AllArgsConstructor
class Pop extends Genre{}

@Builder
@AllArgsConstructor
class JPop extends Genre{}

@Builder
@AllArgsConstructor
class Game extends Genre{}