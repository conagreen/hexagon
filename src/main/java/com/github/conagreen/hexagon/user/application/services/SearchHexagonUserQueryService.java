package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.application.port.in.SearchHexagonUserQuery;
import com.github.conagreen.hexagon.user.application.port.in.SearchHexagonUserQueryResult;
import com.github.conagreen.hexagon.user.application.port.in.SearchHexagonUserUseCase;
import com.github.conagreen.hexagon.user.application.port.out.LoadHexagonUserPort;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.HexagonUserId;
import org.springframework.stereotype.Service;

@Service
public class SearchHexagonUserQueryService implements SearchHexagonUserUseCase {

    private final LoadHexagonUserPort loadHexagonUserPort;

    public SearchHexagonUserQueryService(LoadHexagonUserPort loadHexagonUserPort) {
        this.loadHexagonUserPort = loadHexagonUserPort;
    }

    @Override
    public SearchHexagonUserQueryResult execute(SearchHexagonUserQuery query) {
        final HexagonUser foundUser = loadHexagonUserPort
                .loadById(new HexagonUserId(query.getUserId()));
        return new SearchHexagonUserQueryResult(
                foundUser.getUserId().getId(),
                foundUser.getUserProfile().getNickname().getNickname(),
                foundUser.getUserProfile().getEmail().getEmail()
        );
    }
}
