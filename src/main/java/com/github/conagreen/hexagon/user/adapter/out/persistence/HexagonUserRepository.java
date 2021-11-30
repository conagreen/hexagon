package com.github.conagreen.hexagon.user.adapter.out.persistence;

import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.HexagonUserId;

public interface HexagonUserRepository {

    HexagonUser findByEmail(Email email);

    HexagonUser findById(HexagonUserId hexagonUserId);

    void save(HexagonUser hexagonUser);

    HexagonUser update(HexagonUser hexagonUser);
}
