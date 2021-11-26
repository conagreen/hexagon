package com.github.conagreen.hexagon.user.adapter.in.api;

import com.github.conagreen.hexagon.user.application.port.in.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        log.info("회원가입 요청 (email : {})", body.getEmail() );
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
        log.info("유저 정보 조회 요청 (조회 대상 userId : {})", userId);
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
