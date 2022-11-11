package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    public boolean canPrincipalAccessUser(final long userId) {
        final UserRole userRole = UserPrincipalUtils.getUserPrincipalRole();
        if (userRole == UserRole.MOD || userRole == UserRole.ADMIN) {
            return true;
        }
        return userId == UserPrincipalUtils.getUserPrincipalId();
    }
}
