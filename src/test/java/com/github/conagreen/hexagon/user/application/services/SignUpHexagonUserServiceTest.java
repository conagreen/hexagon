package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.adapter.out.persistence.HexagonUserPersistenceAdapter;
import com.github.conagreen.hexagon.user.adapter.out.persistence.HexagonUserRepository;
import com.github.conagreen.hexagon.user.adapter.out.persistence.InmemoryHexagonUserRepository;
import com.github.conagreen.hexagon.user.application.port.in.SignUpHexagonUserCommand;
import com.github.conagreen.hexagon.user.application.port.out.LoadHexagonUserPort;
import com.github.conagreen.hexagon.user.application.port.out.SaveHexagonUserPort;
import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.Nickname;
import com.github.conagreen.hexagon.user.domain.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원가입 서비스 테스트")
class SignUpHexagonUserServiceTest {

    SignUpHexagonUserService signUpHexagonUserService;
    HexagonUserRepository hexagonUserRepository;

    @BeforeEach
    void beforeEach() {
        hexagonUserRepository = new InmemoryHexagonUserRepository();
        LoadHexagonUserPort loadHexagonUserPort = new HexagonUserPersistenceAdapter(hexagonUserRepository);
        SaveHexagonUserPort saveHexagonUserPort = new HexagonUserPersistenceAdapter(hexagonUserRepository);
        signUpHexagonUserService = new SignUpHexagonUserService(loadHexagonUserPort, saveHexagonUserPort);
    }

    @DisplayName("회원가입")
    @Test
    void signUp() {
        // given
        SignUpHexagonUserCommand command = new SignUpHexagonUserCommand(
                "test@hexgon.com",
                "cona",
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );

        // when
        signUpHexagonUserService.execute(command);

        // then
        HexagonUser result = hexagonUserRepository.findByEmail(new Email(command.getEmail()));
        assertEquals(result.getUserProfile().getEmail().getEmail(), command.getEmail());
    }
}