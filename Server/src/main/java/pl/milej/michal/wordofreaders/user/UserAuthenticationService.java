package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    public boolean canPrincipalAccessUser(final Long userId) {
        if (hasPrincipalImportantRole()) {
            return true;
        }
        return userId.equals(UserPrincipalUtils.getUserPrincipalId());
    }

    public boolean canPrincipalAccessUser(final String username) {
        if (hasPrincipalImportantRole()) {
            return true;
        }
        return username.equals(UserPrincipalUtils.getUserPrincipalUsername());
    }

    private boolean hasPrincipalImportantRole() {
        final UserRole userRole = UserPrincipalUtils.getUserPrincipalRole();
        return userRole == UserRole.MOD || userRole == UserRole.ADMIN;
    }
}
