package com.github.conagreen.hexagon.user.application.port.in;

public interface SearchHexagonUserUseCase {
    SearchHexagonUserQueryResult execute(SearchHexagonUserQuery query);
}
