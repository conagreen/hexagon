package com.github.conagreen.hexagon.user.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 이용 고객을 나타내는 도메인
 */
@Getter
@ToString
public class HexagonUser {

    private final HexagonUserId userId;

    private final UserProfile userProfile;

    private final LocalDateTime createAt;

    private final LocalDateTime lastModifiedAt;

    private HexagonUser(HexagonUserId userId, UserProfile userProfile, LocalDateTime createAt, LocalDateTime lastModifiedAt) {
        this.userId = userId;
        this.userProfile = userProfile;
        this.createAt = createAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public static HexagonUser createNewUser(UserProfile userProfile) {
        final LocalDateTime now = LocalDateTime.now();
        return new HexagonUser(new HexagonUserId(), userProfile, now, now);
    }
}
