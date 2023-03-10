package com.server.stakantoserver.entity;

import com.server.stakantoserver.controller.dto.request.Genre;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.server.stakantoserver.controller.dto.request.Genre.*;
import static com.server.stakantoserver.controller.dto.request.Genre.kPop;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String password;
    private String accountID;
    private int kPop;
    private int pop;
    private int jPop;
    private int game;

    @Builder
    public User(String name, String image, String password, String accountID) {
        this.name = name;
        this.image = image;
        this.password = password;
        this.accountID = accountID;
        this.game = 0;
        this.jPop = 0;
        this.kPop = 0;
        this.pop = 0;
    }

    public User updateScore(Genre kind, int score) {
        switch (kind) {
            case kPop:
                this.kPop = score;
                break;
            case pop:
                this.pop = score;
                break;
            case jPop:
                this.jPop = score;
                break;
            case game:
                this.game = score;
                break;
        }
        return this;
    }
}
