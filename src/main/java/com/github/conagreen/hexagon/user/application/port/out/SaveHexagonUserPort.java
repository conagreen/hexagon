package com.github.conagreen.hexagon.user.application.port.out;

import com.github.conagreen.hexagon.user.domain.HexagonUser;

public interface SaveHexagonUserPort {
    void save(HexagonUser hexagonUser);
}
