package com.github.conagreen.hexagon.user.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@ToString
public class Email {

    private static final Pattern EMAIL_ID_PATTERN = Pattern.compile("([A-Za-z0-9\\-_]+)");

    private final String email;

    public Email(String email) {
        checkValidation(email);
        this.email = email.toLowerCase();
    }

    private void checkValidation(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain at sign");
        }

        final String[] idAndHost = email.split("@");
        final String id = idAndHost[0];
        if (!EMAIL_ID_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Malformed email");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
