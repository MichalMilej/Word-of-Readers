package pl.milej.michal.worldofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    public boolean canPrincipalAccessUser(final Long userId) {
        return userId.equals(UserPrincipalUtils.getUserPrincipalId());
    }

    public boolean canPrincipalAccessUser(final String username) {
        return username.equals(UserPrincipalUtils.getUserPrincipalUsername());
    }
}
