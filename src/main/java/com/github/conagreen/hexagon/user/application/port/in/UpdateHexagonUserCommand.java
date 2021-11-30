package com.github.conagreen.hexagon.user.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateHexagonUserCommand {
    private final String userId;
    private final String nickname;
    private final String profileImageUrl;
    private final String bio;
}
