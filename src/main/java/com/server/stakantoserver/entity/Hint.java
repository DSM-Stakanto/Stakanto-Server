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
public class Hint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hint1;
    private String hint2;
    private String hint3;

    @Builder
    public Hint(int hint1, String hint2, String hint3) {
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.hint3 = hint3;
    }
}
