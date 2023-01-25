package com.server.stakantoserver.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String answer;
    private int start_at;

    @OneToOne
    @JoinColumn(name = "hint_id")
    private Hint hint;

    @Builder
    public Music(String code, String name, String answer, int start_at, Hint hint) {
        this.answer = answer;
        this.code = code;
        this.name = name;
        this.start_at = start_at;
        this.hint = hint;
    }
}
