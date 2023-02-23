package com.server.stakantoserver.controller;

import com.server.stakantoserver.controller.dto.request.LogRequest;
import com.server.stakantoserver.controller.dto.request.MusicInfoRequest;
import com.server.stakantoserver.controller.dto.response.RecentlyLogResponse;
import com.server.stakantoserver.controller.dto.response.UserResponse;
import com.server.stakantoserver.controller.dto.response.findRank.TopRankResponse;
import com.server.stakantoserver.entity.Music;
import com.server.stakantoserver.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final MainService mainService;

    @GetMapping("/top-rank")
    public TopRankResponse getTopRank() {
        return mainService.findRank();
    }

    @PostMapping("/admin/music")
    public void recordMusicInfo(@RequestBody MusicInfoRequest request) {
        mainService.recordMusicInfo(request);
    }

    @GetMapping("/music/{genre}")
    public List<Music> returnMusic(@PathVariable String genre) {
        return mainService.returnMusicList(genre);
    }

    @PostMapping("/log")
    public void recordActivity(@RequestBody LogRequest request) {
        mainService.recordLog(request);
    }

    @GetMapping("/log/{genre}")
    public RecentlyLogResponse recentlyLog(@PathVariable String genre) {
        return mainService.recentlyLog(genre);
    }

    @GetMapping("/user")
    public UserResponse userResponse() {
        return mainService.userResponse();
    }
}
