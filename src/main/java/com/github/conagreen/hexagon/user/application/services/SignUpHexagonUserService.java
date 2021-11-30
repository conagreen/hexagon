package com.github.conagreen.hexagon.user.application.services;

import com.github.conagreen.hexagon.user.application.port.in.EmailAlreadyTakenException;
import com.github.conagreen.hexagon.user.application.port.in.SignUpHexagonUserCommand;
import com.github.conagreen.hexagon.user.application.port.in.SignUpHexagonUserUseCase;
import com.github.conagreen.hexagon.user.application.port.out.LoadHexagonUserPort;
import com.github.conagreen.hexagon.user.application.port.out.SaveHexagonUserPort;
import com.github.conagreen.hexagon.user.domain.Email;
import com.github.conagreen.hexagon.user.domain.HexagonUser;
import com.github.conagreen.hexagon.user.domain.Nickname;
import com.github.conagreen.hexagon.user.domain.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SignUpHexagonUserService implements SignUpHexagonUserUseCase {

    private final LoadHexagonUserPort loadHexagonUserPort;
    private final SaveHexagonUserPort saveHexagonUserPort;

    public SignUpHexagonUserService(LoadHexagonUserPort loadHexagonUserPort, SaveHexagonUserPort saveHexagonUserPort) {
        this.loadHexagonUserPort = loadHexagonUserPort;
        this.saveHexagonUserPort = saveHexagonUserPort;
    }

    @Override
    public void execute(SignUpHexagonUserCommand command) {
        final Email email = new Email(command.getEmail());
        final HexagonUser maybeUser = loadHexagonUserPort.loadByEmail(email);
        checkEmailAlreadyExists(maybeUser);

        final HexagonUser newUser = HexagonUser.createNewUser(
                UserProfile.create(
                        new Nickname(command.getNickname()),
                        email,
                        command.getProfileImageUrl(),
                        command.getBio()
                )
        );

        saveHexagonUserPort.save(newUser);
    }

    private void checkEmailAlreadyExists(HexagonUser maybeUser) {
        if (maybeUser != null) {
            throw new EmailAlreadyTakenException("Email already taken.");
        }
    }
}
