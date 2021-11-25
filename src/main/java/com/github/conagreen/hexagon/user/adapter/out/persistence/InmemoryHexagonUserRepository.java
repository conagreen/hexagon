package com.github.conagreen.hexagon.user.adapter.out.persistence;

import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.HexagonUserId;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InmemoryHexagonUserRepository implements HexagonUserRepository {

    private final Map<Email, HexagonUser> dataStore = new HashMap<>();

    @Override
    public HexagonUser findByEmail(Email email) {
        return dataStore.get(email);
    }

    @Override
    public HexagonUser findById(HexagonUserId hexagonUserId) {
        for (HexagonUser hexagonUser : dataStore.values()) {
            if (hexagonUser.getUserId().equals(hexagonUserId)) {
                return hexagonUser;
            }
        }
        return null;
    }

    @Override
    public void save(HexagonUser hexagonUser) {
        dataStore.put(hexagonUser.getUserProfile().getEmail(), hexagonUser);
    }
}