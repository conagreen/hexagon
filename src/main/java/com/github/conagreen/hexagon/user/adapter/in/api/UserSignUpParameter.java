package com.github.conagreen.hexagon.user.adapter.in.api;

import lombok.Getter;

@Getter
public class UserSignUpParameter {
    private String email;
    private String nickname;
    private String profileImageUrl;
    private String bio;
}
