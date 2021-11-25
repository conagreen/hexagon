package com.github.conagreen.hexagon.user.application.port.out;

import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.HexagonUserId;

public interface LoadHexagonUserPort {
    HexagonUser loadByEmail(Email email);
    HexagonUser loadById(HexagonUserId hexagonUserId);
}
