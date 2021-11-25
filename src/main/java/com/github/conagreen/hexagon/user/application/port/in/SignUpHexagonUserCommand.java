package com.github.conagreen.hexagon.user.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class SignUpHexagonUserCommand {
    private final String email;
    private final String nickname;
    private final String profileImageUrl;
    private final String bio;
}
