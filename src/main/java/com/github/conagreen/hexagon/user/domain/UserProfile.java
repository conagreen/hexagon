package com.github.conagreen.hexagon.user.domain;

import com.github.conagreen.hexagon.user.application.port.in.UpdateHexagonUserCommand;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserProfile {

    private Nickname nickname;
    private final Email email;
    private String imageUrl;
    private String bio;

    private UserProfile(Nickname nickname, Email email, String imageUrl, String bio) {
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }

    public static UserProfile create(Nickname nickname, Email email, String imageUrl, String bio) {
        return new UserProfile(nickname, email, imageUrl, bio);
    }

    public void update(UpdateHexagonUserCommand command) {
        this.nickname = nickname.updateNickname(command.getNickname());
        this.imageUrl = command.getProfileImageUrl();
        this.bio = command.getBio();
    }
}
