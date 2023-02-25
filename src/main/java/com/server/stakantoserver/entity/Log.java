package com.server.stakantoserver.entity;

import com.server.stakantoserver.controller.dto.request.Genre;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int point;
    private LocalDateTime createdAt;

    @Builder
    public Log(User user, Genre genre, int point) {
        this.user = user;
        this.point = point;
        this.genre = genre;
        this.createdAt = LocalDateTime.now();
    }
}
