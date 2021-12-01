package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.adapter.out.persistence.HexagonUserPersistenceAdapter;
import com.github.conagreen.hexagon.user.adapter.out.persistence.HexagonUserRepository;
import com.github.conagreen.hexagon.user.adapter.out.persistence.InmemoryHexagonUserRepository;
import com.github.conagreen.hexagon.user.application.port.in.UpdateHexagonUserCommand;
import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.Nickname;
import com.github.conagreen.hexagon.user.domain.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 정보 수정 서비스 테스트")
class UpdateHexagonUserServiceTest {

    HexagonUserRepository hexagonUserRepository;
    UpdateHexagonUserService updateHexagonUserService;

    @BeforeEach
    void beforeEach() {
        hexagonUserRepository = new InmemoryHexagonUserRepository();
        HexagonUserPersistenceAdapter persistenceAdapter = new HexagonUserPersistenceAdapter(hexagonUserRepository);
        updateHexagonUserService = new UpdateHexagonUserService(persistenceAdapter, persistenceAdapter);
    }

    @DisplayName("닉네임, 이미지, 소개 수정")
    @Test
    void update() {
        // given
        UserProfile userProfile = UserProfile.create(
                new Nickname("cona"),
                new Email("test@hexagon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );
        HexagonUser hexagonUser = HexagonUser.createNewUser(userProfile);
        hexagonUserRepository.save(hexagonUser);

        // when
        UpdateHexagonUserCommand updateHexagonUserCommand = new UpdateHexagonUserCommand(
                hexagonUser.getUserId().getId(),
                "미진",
                "https://github.com/conagreen",
                "서버 개발자(이고싶다)"
        );
        updateHexagonUserService.execute(updateHexagonUserCommand);

        // then
        HexagonUser result = hexagonUserRepository.findById(hexagonUser.getUserId());
        assertEquals(result.getUserProfile().getNickname().getNickname(), "미진");
        assertEquals(result.getUserProfile().getImageUrl(), "https://github.com/conagreen");
        assertEquals(result.getUserProfile().getBio(), "서버 개발자(이고싶다)");
    }
}