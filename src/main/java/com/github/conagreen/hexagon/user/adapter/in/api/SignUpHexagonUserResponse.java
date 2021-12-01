package com.github.conagreen.hexagon.user.adapter.in.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpHexagonUserResponse {
    private final String userId;
    private final String nickname;
}
