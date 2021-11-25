package com.github.conagreen.hexagon.user.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 작업 기사를 나타내는 도메인
 */
@Getter
@ToString
public class TaskerUser {

    private final TaskerUserId userId;

    private final UserProfile userProfile;

    private final LocalDateTime createdAt;

    private final LocalDateTime lastModifiedAt;

    private TaskerUser(TaskerUserId userId, UserProfile userProfile, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.userId = userId;
        this.userProfile = userProfile;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
