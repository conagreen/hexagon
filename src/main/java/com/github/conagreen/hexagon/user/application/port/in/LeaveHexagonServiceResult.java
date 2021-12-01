package com.github.conagreen.hexagon.user.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LeaveHexagonServiceResult {
    private final String userId;
    private final String email;
}
