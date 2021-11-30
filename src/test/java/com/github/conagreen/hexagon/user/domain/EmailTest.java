package com.github.conagreen.hexagon.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 이메일 도메인 테스트")
class EmailTest {

    @DisplayName("이메일은 대소문자 구분을 하지 않음")
    @Test
    void testEmailIsCaseInsensitive() {
        final Email lowerCase = new Email("test-user@hexagon.com");
        final Email upperCase = new Email("TEST-USER@HEXAGON.COM");

        assertThat(lowerCase).isEqualTo(upperCase);
    }

    @DisplayName("이메일이 null인 경우 IllegalArgumentException 발생")
    @Test
    void testEmailCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @DisplayName("이메일에 @ 기호가 없는 경우 IllegalArgumentException 발생")
    @Test
    void testEmailShouldContainAtSign() {
        assertThrows(IllegalArgumentException.class, () -> new Email("test-user"));
    }

    @DisplayName("이메일 형식이 맞지 않는 경우 IllegalArgumentException 발생")
    @Test
    void testMalformedEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Email("test-user!!@hexagon.com"));
    }
}