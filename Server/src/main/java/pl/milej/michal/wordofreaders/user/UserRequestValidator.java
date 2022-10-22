package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.milej.michal.wordofreaders.exception.BadRequestException;

@Component
@RequiredArgsConstructor
public class UserRequestValidator {

    final UserRepository userRepository;

    public void validateUserRequest(final UserRequest userRequest) {
        validateUsername(userRequest.getUsername());
        validatePassword(userRequest.getPassword());
        validateEmail(userRequest.getEmail());
    }

    private void validateUsername(final String username) {
        if (username == null || username.length() < 3) {
            throw new BadRequestException("Username should be at least 3 characters long");
        }
        if (userRepository.findByUsernameEqualsIgnoreCase(username).isPresent()) {
            throw new BadRequestException("Username already in database");
        }
    }

    private void validatePassword(final String password) {
        if (password == null || password.length() < 8) {
            throw new BadRequestException("Password should be at least 8 characters long");
        }
    }

    private void validateEmail(final String email) {
        if (email == null || !email.contains("@") || email.length() < 4) {
            throw new BadRequestException("E-mail address is not correct");
        }
        if (userRepository.findByEmailEqualsIgnoreCase(email).isPresent()) {
            throw new BadRequestException("Email already in database");
        }
    }
}
