package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.application.port.in.LeaveHexagonServiceResult;
import com.github.conagreen.hexagon.user.application.port.in.LeaveHexagonServiceUseCase;
import com.github.conagreen.hexagon.user.application.port.out.DeleteHexagonUserPort;
import com.github.conagreen.hexagon.user.application.port.out.LoadHexagonUserPort;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.HexagonUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LeaveHexagonServiceService implements LeaveHexagonServiceUseCase {

    private final LoadHexagonUserPort loadHexagonUserPort;
    private final DeleteHexagonUserPort deleteHexagonUserPort;

    @Override
    public LeaveHexagonServiceResult execute(String userId) {
        final HexagonUser foundUser = loadHexagonUserPort.loadById(new HexagonUserId(userId));
        deleteHexagonUserPort.delete(foundUser);

        return new LeaveHexagonServiceResult(
                foundUser.getUserId().getId(),
                foundUser.getUserProfile().getEmail().getEmail());
    }
}
