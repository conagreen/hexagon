package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.adapter.out.persistence.HexagonUserPersistenceAdapter;
import com.github.conagreen.hexagon.user.adapter.out.persistence.HexagonUserRepository;
import com.github.conagreen.hexagon.user.adapter.out.persistence.InmemoryHexagonUserRepository;
import com.github.conagreen.hexagon.user.application.port.in.LeaveHexagonServiceCommand;
import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.Nickname;
import com.github.conagreen.hexagon.user.domain.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원탈퇴 서비스 테스트")
class LeaveHexagonServiceServiceTest {

    HexagonUserRepository hexagonUserRepository;
    LeaveHexagonServiceService leaveHexagonServiceService;

    @BeforeEach
    void beforeEach() {
        hexagonUserRepository = new InmemoryHexagonUserRepository();
        HexagonUserPersistenceAdapter adapter = new HexagonUserPersistenceAdapter(hexagonUserRepository);
        leaveHexagonServiceService = new LeaveHexagonServiceService(adapter, adapter);
    }

    @DisplayName("회원탈퇴")
    @Test
    void leave() {
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
        LeaveHexagonServiceCommand command = new LeaveHexagonServiceCommand(hexagonUser.getUserId().getId());
        leaveHexagonServiceService.execute(command);

        // then
        assertNull(hexagonUserRepository.findById(hexagonUser.getUserId()));
    }
}