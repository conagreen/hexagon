package com.github.conagreen.hexagon.user.adapter.in.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchHexagonUserResponse {
    private String userId;
    private String nickname;
    private String email;
}
