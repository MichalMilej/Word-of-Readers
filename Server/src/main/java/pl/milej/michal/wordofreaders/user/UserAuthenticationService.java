package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    public boolean canPrincipalAccessUser(final Long userId) {
        if (UserPrincipalUtils.hasPrincipalModOrAdminRole()) {
            return true;
        }
        return userId.equals(UserPrincipalUtils.getUserPrincipalId());
    }

    public boolean canPrincipalAccessUser(final String username) {
        if (UserPrincipalUtils.hasPrincipalModOrAdminRole()) {
            return true;
        }
        return username.equals(UserPrincipalUtils.getUserPrincipalUsername());
    }
}
