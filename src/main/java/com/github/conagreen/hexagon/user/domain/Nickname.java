package com.github.conagreen.hexagon.user.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Nickname {

    private String nickname;

    public Nickname(String nickname) {
        checkValidation(nickname);
        this.nickname = nickname;
    }

    private void checkValidation(String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException("Nickname cannot be null.");
        }

        if (nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname cannot be empty.");
        }
    }

    public Nickname updateNickname(String nickname) {
        checkValidation(nickname);
        this.nickname = nickname;

        return this;
    }
}
