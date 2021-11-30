package com.github.conagreen.hexagon.user.application.port.in;

public interface UpdateHexagonUserUseCase {
    UpdateHexagonUserResult execute(UpdateHexagonUserCommand command);
}
