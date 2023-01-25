package com.server.stakantoserver.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    public User(String name, String image, String password, String accountID, int kPop, int pop, int jPop, int game) {
        this.name = name;
        this.image = image;
        this.password = password;
        this.accountID = accountID;
        this.game = game;
        this.jPop = jPop;
        this.kPop = kPop;
        this.pop = pop;
    }
}
