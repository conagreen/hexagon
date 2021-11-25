package com.github.conagreen.hexagon.user.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserProfile {

    private final Nickname nickname;
    public final Email email;
    private final String imageUrl;
    private final String bio;

    private UserProfile(Nickname nickname, Email email, String imageUrl, String bio) {
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }

    public static UserProfile create(Nickname nickname, Email email, String imageUrl, String bio) {
        return new UserProfile(nickname, email, imageUrl, bio);
    }
}
