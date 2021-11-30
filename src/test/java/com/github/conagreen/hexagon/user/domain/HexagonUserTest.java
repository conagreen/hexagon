package com.github.conagreen.hexagon.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("헥사곤 사용자 엔티티 테스트")
class HexagonUserTest {

    @DisplayName("생성 규칙 테스트")
    @Test
    void testHexagonUserCreationRuleTest() {
        final String nickname = "닉네임";
        final String email = "test-user@hexagon.com";
        final String imageUrl = "https://avatars.githubusercontent.com/u/68418154?v=4";
        final String bio ="서버 개발자입니다.";

        final UserProfile userProfile = UserProfile.create(
                new Nickname(nickname),
                new Email(email),
                imageUrl,
                bio
        );

        final HexagonUser newHexagonUser = HexagonUser.createNewUser(userProfile);
        final UserProfile profile = newHexagonUser.getUserProfile();

        assertEquals(nickname, profile.getNickname().getNickname());
        assertEquals(email, profile.getEmail().getEmail());
        assertEquals(imageUrl, userProfile.getImageUrl());
        assertEquals(bio, profile.getBio());

    }
}