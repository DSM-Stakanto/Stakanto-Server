package com.server.stakantoserver.service;

import com.server.stakantoserver.controller.dto.response.findRank.Genre;
import com.server.stakantoserver.controller.dto.response.findRank.TopRankResponse;
import com.server.stakantoserver.entity.User;
import com.server.stakantoserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private final UserRepository userRepository;

    public TopRankResponse findRank() {
        List<Genre> list = new ArrayList<>();
        User user = userRepository.findAll(Sort.by(Sort.Direction.DESC, "kPop")).get(0);
        list.add(Genre.builder()
                        .genre("k-pop")
                        .name(user.getName())
                        .score(user.getKPop())
                .build());
        user = userRepository.findAll(Sort.by(Sort.Direction.DESC, "jPop")).get(0);
        list.add(Genre.builder()
                        .genre("j-pop")
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
}
