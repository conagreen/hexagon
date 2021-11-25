package com.github.conagreen.hexagon.user.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchHexagonUserQueryResult {
    private final String hexagonUserId;
    private final String nickname;
    private final String email;
}
