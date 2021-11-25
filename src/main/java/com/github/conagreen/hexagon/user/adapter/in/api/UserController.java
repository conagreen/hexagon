package com.github.conagreen.hexagon.user.adapter.in.api;

import com.github.conagreen.hexagon.user.application.port.in.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SignUpHexagonUserUseCase signUpHexagonUserUseCase;
    private final SearchHexagonUserUseCase searchHexagonUserUseCase;

    public UserController(SignUpHexagonUserUseCase signUpHexagonUserUseCase, SearchHexagonUserUseCase searchHexagonUserUseCase) {
        this.signUpHexagonUserUseCase = signUpHexagonUserUseCase;
        this.searchHexagonUserUseCase = searchHexagonUserUseCase;
    }

    @PostMapping
    public void signUp(@RequestBody UserSignUpParameter body) {
        signUpHexagonUserUseCase.execute(
                new SignUpHexagonUserCommand(
                        body.getEmail(),
                        body.getNickname(),
                        body.getProfileImageUrl(),
                        body.getBio()
                )
        );
    }

    @GetMapping("/{userId}")
    public SearchHexagonUserResponse searchUser(@PathVariable String userId){
        final SearchHexagonUserQueryResult queryResult = searchHexagonUserUseCase.execute(
                new SearchHexagonUserQuery(userId)
        );

        return new SearchHexagonUserResponse(
                queryResult.getHexagonUserId(),
                queryResult.getNickname(),
                queryResult.getEmail()
        );
    }
}
