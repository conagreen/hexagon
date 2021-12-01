package com.github.conagreen.hexagon.user.adapter.out.persistence;

import com.github.conagreen.hexagon.user.application.port.in.UpdateHexagonUserCommand;
import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.Nickname;
import com.github.conagreen.hexagon.user.domain.UserProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("헥사곤 사용자 인메모리 저장소 테스트")
class InmemoryHexagonUserRepositoryTest {

    InmemoryHexagonUserRepository repository = new InmemoryHexagonUserRepository();

    @DisplayName("회원가입")
    @Test
    void save() {
        // given
        UserProfile userProfile = UserProfile.create(
                new Nickname("cona"),
                new Email("test@hexgon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );

        HexagonUser hexagonUser = HexagonUser.createNewUser(userProfile);

        // when
        repository.save(hexagonUser);

        // then
        HexagonUser result = repository.findById(hexagonUser.getUserId());
        assertEquals(result, hexagonUser);
    }

    @DisplayName("Email로 사용자 조회")
    @Test
    void findByEmail(){
        // given
        UserProfile userProfile1 = UserProfile.create(
                new Nickname("cona"),
                new Email("cona@hexgon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );

        UserProfile userProfile2 = UserProfile.create(
                new Nickname("미진"),
                new Email("mijin@hexgon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(이고싶다)"
        );

        HexagonUser hexagonUser1 = HexagonUser.createNewUser(userProfile1);
        HexagonUser hexagonUser2 = HexagonUser.createNewUser(userProfile2);

        repository.save(hexagonUser1);
        repository.save(hexagonUser2);

        // when
        HexagonUser result = repository.findByEmail(new Email("cona@hexgon.com"));

        // then
        assertEquals(result, hexagonUser1);
    }

    @DisplayName("Id로 사용자 조회")
    @Test
    void findById(){
        // given
        UserProfile userProfile1 = UserProfile.create(
                new Nickname("cona"),
                new Email("cona@hexgon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );

        UserProfile userProfile2 = UserProfile.create(
                new Nickname("미진"),
                new Email("mijin@hexgon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(이고싶다)"
        );

        HexagonUser hexagonUser1 = HexagonUser.createNewUser(userProfile1);
        HexagonUser hexagonUser2 = HexagonUser.createNewUser(userProfile2);

        repository.save(hexagonUser1);
        repository.save(hexagonUser2);

        // when
        HexagonUser result = repository.findById(hexagonUser1.getUserId());

        // then
        assertEquals(result, hexagonUser1);
    }

    @DisplayName("사용자 정보 수정")
    @Test
    void update() {
        // given
        UserProfile userProfile = UserProfile.create(
                new Nickname("cona"),
                new Email("cona@hexgon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );

        HexagonUser hexagonUser = HexagonUser.createNewUser(userProfile);
        repository.save(hexagonUser);

        // when
        hexagonUser.update(new UpdateHexagonUserCommand(
                hexagonUser.getUserId().getId(),
                "미진",
                "https://github.com/conagreen",
                "하하호호"
        ));
        repository.update(hexagonUser);

        // then
        assertEquals(hexagonUser.getUserProfile().getNickname().getNickname(), "미진");
        assertEquals(hexagonUser.getUserProfile().getImageUrl(), "https://github.com/conagreen");
        assertEquals(hexagonUser.getUserProfile().getBio(), "하하호호");
    }

    @DisplayName("서비스 탈퇴")
    @Test
    void delete() {
        // given
        UserProfile userProfile = UserProfile.create(
                new Nickname("cona"),
                new Email("cona@hexgon.com"),
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );

        HexagonUser hexagonUser = HexagonUser.createNewUser(userProfile);
        repository.save(hexagonUser);

        // when
        repository.delete(hexagonUser);
        HexagonUser result = repository.findByEmail(hexagonUser.getUserProfile().getEmail());

        // then
        assertNull(result);
    }

}