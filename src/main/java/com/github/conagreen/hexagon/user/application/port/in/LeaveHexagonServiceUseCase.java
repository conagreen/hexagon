package com.github.conagreen.hexagon.user.application.port.in;

public interface LeaveHexagonServiceUseCase {
    LeaveHexagonServiceResult execute(LeaveHexagonServiceCommand command);
}
