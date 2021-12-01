package com.github.conagreen.hexagon.user.application.port.in;

public interface SignUpHexagonUserUseCase {
    SignUpHexagonUserResult execute(SignUpHexagonUserCommand command);
}
