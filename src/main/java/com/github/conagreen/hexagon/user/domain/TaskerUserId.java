package com.github.conagreen.hexagon.user.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Getter
@ToString
public class TaskerUserId {

    private final String id;

    public TaskerUserId() {
        this.id = UUID.randomUUID().toString();
    }

    public TaskerUserId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskerUserId that = (TaskerUserId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
