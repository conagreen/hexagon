package com.github.conagreen.hexagon.user.adapter.out.persistence;

import com.github.conagreen.hexagon.user.application.port.out.LoadHexagonUserPort;
import com.github.conagreen.hexagon.user.application.port.out.SaveHexagonUserPort;
import com.github.conagreen.hexagon.user.application.port.out.UpdateHexagonUserPort;
import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.HexagonUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HexagonUserPersistenceAdapter implements SaveHexagonUserPort, LoadHexagonUserPort, UpdateHexagonUserPort {

    private final HexagonUserRepository hexagonUserRepository;

    @Override
    public HexagonUser loadByEmail(Email email) {
        return hexagonUserRepository.findByEmail(email);
    }

    @Override
    public HexagonUser loadById(HexagonUserId hexagonUserId) {
        return hexagonUserRepository.findById(hexagonUserId);
    }

    @Override
    public void save(HexagonUser hexagonUser) {
        hexagonUserRepository.save(hexagonUser);
    }

    @Override
    public HexagonUser update(HexagonUser hexagonUser) {
        return hexagonUserRepository.update(hexagonUser);
    }
}
