package com.server.stakantoserver.service;

import com.server.stakantoserver.controller.dto.request.LogRequest;
import com.server.stakantoserver.controller.dto.request.MusicInfoRequest;
import com.server.stakantoserver.controller.dto.response.RecentlyLogResponse;
import com.server.stakantoserver.controller.dto.response.findRank.Genre;
import com.server.stakantoserver.controller.dto.response.findRank.TopRankResponse;
import com.server.stakantoserver.entity.Log;
import com.server.stakantoserver.entity.Music;
import com.server.stakantoserver.entity.User;
import com.server.stakantoserver.repository.HintRepository;
import com.server.stakantoserver.repository.LogRepository;
import com.server.stakantoserver.repository.MusicRepository;
import com.server.stakantoserver.repository.UserRepository;
import com.server.stakantoserver.security.details.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MainService {

    private final UserRepository userRepository;

    private final MusicRepository musicRepository;

    private final HintRepository hintRepository;

    private final LogRepository logRepository;

    public TopRankResponse findRank() {
        List<Genre> list = new ArrayList<>();
        User user = userRepository.findAll(Sort.by(Sort.Direction.DESC, "kPop")).get(0);
        list.add(Genre.builder()
                        .genre("kPop")
                        .name(user.getName())
                        .score(user.getKPop())
                .build());
        user = userRepository.findAll(Sort.by(Sort.Direction.DESC, "jPop")).get(0);
        list.add(Genre.builder()
                        .genre("jPop")
                        .name(user.getName())
                        .score(user.getJPop())
                .build());
        user = userRepository.findAll(Sort.by(Sort.Direction.DESC, "pop")).get(0);
        list.add(Genre.builder()
                        .genre("pop")
                        .name(user.getName())
                        .score(user.getPop())
                .build());
        user = userRepository.findAll(Sort.by(Sort.Direction.DESC, "game")).get(0);
        list.add(Genre.builder()
                        .genre("game")
                        .name(user.getName())
                        .score(user.getGame())
                .build());
        return new TopRankResponse(list);
    }

    public void recordMusicInfo(MusicInfoRequest request) {
        if(musicRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("the music is already in database");
        }
        hintRepository.save(request.getHint());
        musicRepository.save(Music.builder()
                        .answer(request.getAnswer())
                        .code(request.getCode())
                        .hint(request.getHint())
                        .name(request.getName())
                        .genre(request.getGenre())
                        .start_at(request.getStartAt())
                .build());
    }

    public List<Music> returnMusicList(String genre) {
        List<Music> list = musicRepository.findByGenre(genre);
        List<Music> result = new ArrayList<>();
        Random rand = new Random();
        while (true) {
            List<Music> rm = new ArrayList<>();
            if (result.size() == 4) break; // result.size() == 20
            for (Music music : list) {
                if (rand.nextBoolean()) {
                    result.add(music);
                    rm.add(music);
                }
            }
            list = list.stream().filter(l->!rm.contains(l)).collect(Collectors.toList());
        }
        return result;
    }

    public void recordLog(LogRequest request) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        switch (request.getGenre()) {
            case "kPop":
                if (request.getPoint() > details.getUser().getKPop()) userRepository
                        .save(details.getUser().updateScore(request.getGenre(), request.getPoint()));
                break;
            case "pop":
                if (request.getPoint() > details.getUser().getPop()) userRepository
                        .save(details.getUser().updateScore(request.getGenre(), request.getPoint()));
                break;
            case "jPop":
                if (request.getPoint() > details.getUser().getJPop()) userRepository
                        .save(details.getUser().updateScore(request.getGenre(), request.getPoint()));
                break;
            case "game":
                if (request.getPoint() > details.getUser().getGame()) userRepository
                        .save(details.getUser().updateScore(request.getGenre(), request.getPoint()));
                break;
            default:
                break;
        }
        logRepository.save(Log.builder()
                        .genre(request.getGenre())
                        .user(details.getUser())
                        .point(request.getPoint())
                .build());
    }

    public RecentlyLogResponse recentlyLog(String genre) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Log> list = logRepository.findByUserAndGenreOrderByCreatedAtDesc(details.getUser(), genre);
        List<Integer> result = new ArrayList<>();
        if (list.size() > 4) {
            for (int i = 0; i < 5; i++) {
                result.add(list.get(i).getPoint());
            }
        }
        else for (Log log : list) result.add(log.getPoint());
        return RecentlyLogResponse.builder()
                .genre(genre)
                .scores(result)
                .build();
    }
}
