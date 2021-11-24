package com.github.conagreen.hexagon.user.domain;

import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@ToString
public class HexagonUserId {

    private final String id;

    public HexagonUserId() {
        this.id = UUID.randomUUID().toString();
    }

    public HexagonUserId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HexagonUserId that = (HexagonUserId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
