package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.adapter.out.persistence.HexagonUserPersistenceAdapter;
import com.github.conagreen.hexagon.user.adapter.out.persistence.InmemoryHexagonUserRepository;
import com.github.conagreen.hexagon.user.application.port.in.SearchHexagonUserQuery;
import com.github.conagreen.hexagon.user.application.port.in.SearchHexagonUserQueryResult;
import com.github.conagreen.hexagon.user.application.port.in.SignUpHexagonUserCommand;
import com.github.conagreen.hexagon.user.application.port.in.SignUpHexagonUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 조회 서비스 테스트")
class SearchHexagonUserQueryServiceTest {

    SearchHexagonUserQueryService searchHexagonUserQueryService;
    SignUpHexagonUserService signUpHexagonUserService;

    @BeforeEach
    void beforeEach() {
        HexagonUserPersistenceAdapter persistenceAdapter = new HexagonUserPersistenceAdapter(new InmemoryHexagonUserRepository());
        searchHexagonUserQueryService = new SearchHexagonUserQueryService(persistenceAdapter);
        signUpHexagonUserService = new SignUpHexagonUserService(persistenceAdapter, persistenceAdapter);
    }

    @DisplayName("userId로 사용자 조회")
    @Test
    void findById() {
        // given
        SignUpHexagonUserCommand command = new SignUpHexagonUserCommand(
                "test@hexgon.com",
                "cona",
                "https://avatars.githubusercontent.com/u/68418154?v=4",
                "서버 개발자(가 되고싶어요)"
        );

        SignUpHexagonUserResult result = signUpHexagonUserService.execute(command);

        // when
        SearchHexagonUserQueryResult queryResult = searchHexagonUserQueryService.execute(
                new SearchHexagonUserQuery(result.getUserId()));

        // then
        assertEquals(queryResult.getEmail(), command.getEmail());

    }

}