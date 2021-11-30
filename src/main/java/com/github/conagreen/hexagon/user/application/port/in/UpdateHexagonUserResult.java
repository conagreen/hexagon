package com.github.conagreen.hexagon.user.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateHexagonUserResult {
    private final String userId;
}
