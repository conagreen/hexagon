package com.github.conagreen.hexagon.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 닉네임 도메인 테스트")
class NicknameTest {

    @DisplayName("닉네임이 제약조건을 만족하는 경우 어떠한 예외도 발생하지 않음")
    @Test
    void testCorrectNickname() {
        assertDoesNotThrow(() -> new Nickname("핵사고날"));
    }

    @DisplayName("닉네임이 null인 경우 IllegalArgumentException 발생")
    @Test
    void testNullNickname() {
        assertThrows(IllegalArgumentException.class, () -> new Nickname(null));
    }

    @DisplayName("닉네임이 비어있는 경우 IllegalArgumentException 발생")
    @Test
    void testEmptyNickname() {
        assertThrows(IllegalArgumentException.class, () -> new Nickname(""));
    }

    @DisplayName("닉네임이 공백으로만 되어 있는 경우 IllegalArgumentException 발생")
    @Test
    void testWhiteSpaceNickname() {
        assertThrows(IllegalArgumentException.class, () -> new Nickname("     "));
    }
}