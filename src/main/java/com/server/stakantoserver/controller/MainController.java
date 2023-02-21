package com.server.stakantoserver.controller;

import com.server.stakantoserver.controller.dto.response.findRank.TopRankResponse;
import com.server.stakantoserver.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final MainService mainService;

    @GetMapping("/top-rank")
    public TopRankResponse getTopRank() {
        return mainService.findRank();
    }
}
