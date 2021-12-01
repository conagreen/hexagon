package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.application.port.in.UpdateHexagonUserCommand;
import com.github.conagreen.hexagon.user.application.port.in.UpdateHexagonUserResult;
import com.github.conagreen.hexagon.user.application.port.in.UpdateHexagonUserUseCase;
import com.github.conagreen.hexagon.user.application.port.out.LoadHexagonUserPort;
import com.github.conagreen.hexagon.user.application.port.out.UpdateHexagonUserPort;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.HexagonUserId;
import org.springframework.stereotype.Service;

@Service
public class UpdateHexagonUserService implements UpdateHexagonUserUseCase {

    private final LoadHexagonUserPort loadHexagonUserPort;
    private final UpdateHexagonUserPort updateHexagonUserPort;

    public UpdateHexagonUserService(LoadHexagonUserPort loadHexagonUserPort, UpdateHexagonUserPort updateHexagonUserPort) {
        this.loadHexagonUserPort = loadHexagonUserPort;
        this.updateHexagonUserPort = updateHexagonUserPort;
    }

    @Override
    public UpdateHexagonUserResult execute(UpdateHexagonUserCommand command) {
        HexagonUser foundUser = loadHexagonUserPort
                .loadById(new HexagonUserId(command.getUserId()));

        foundUser.update(command);

        updateHexagonUserPort.update(foundUser);

        return new UpdateHexagonUserResult(
                foundUser.getUserId().getId()
        );
    }
}
