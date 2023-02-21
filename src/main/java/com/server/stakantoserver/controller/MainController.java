package com.server.stakantoserver.controller;

import com.server.stakantoserver.controller.dto.request.MusicInfoRequest;
import com.server.stakantoserver.controller.dto.response.findRank.TopRankResponse;
import com.server.stakantoserver.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final MainService mainService;

    @GetMapping("/top-rank")
    public TopRankResponse getTopRank() {
        return mainService.findRank();
    }

    @PostMapping("/music")
    public void recordMusicInfo(@RequestBody MusicInfoRequest request) {
        mainService.recordMusicInfo(request);
    }
}
