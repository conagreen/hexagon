package com.github.conagreen.hexagon.user.adapter.in.api;

import com.github.conagreen.hexagon.user.application.port.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final SignUpHexagonUserUseCase signUpHexagonUserUseCase;
    private final SearchHexagonUserUseCase searchHexagonUserUseCase;
    private final UpdateHexagonUserUseCase updateHexagonUserUseCase;
    private final LeaveHexagonServiceUseCase leaveHexagonServiceUseCase;

    @PostMapping
    public SignUpHexagonUserResponse signUp(@RequestBody UserSignUpParameter body) {
        log.info("회원가입 요청 (email : {})", body.getEmail());
        final SignUpHexagonUserResult result = signUpHexagonUserUseCase.execute(
                new SignUpHexagonUserCommand(
                        body.getEmail(),
                        body.getNickname(),
                        body.getProfileImageUrl(),
                        body.getBio()
                )
        );

        return new SignUpHexagonUserResponse(
                result.getUserId(),
                result.getNickname()
        );
    }

    @GetMapping("/{userId}")
    public SearchHexagonUserResponse searchUser(@PathVariable String userId) {
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

    @PutMapping("/{userId}")
    public UpdateHexagonUserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateParameter body) {
        log.info("유저 정보 수정 요청 (수정 대상 userId : {})", userId);
        final UpdateHexagonUserResult result = updateHexagonUserUseCase.execute(
                new UpdateHexagonUserCommand(
                        userId,
                        body.getNickname(),
                        body.getProfileImageUrl(),
                        body.getBio())
        );

        return new UpdateHexagonUserResponse(
                result.getUserId()
        );
    }

    @DeleteMapping("/{userId}")
    public LeaveHexagonServiceResponse withdraw(@PathVariable String userId) {
        log.info("서비스 탈퇴 요청 (삭제 대상 userId : {})", userId);
        final LeaveHexagonServiceResult result = leaveHexagonServiceUseCase.execute(userId);
        return new LeaveHexagonServiceResponse(
                result.getUserId(),
                result.getEmail()
        );
    }
}
