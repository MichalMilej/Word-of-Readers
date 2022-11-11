package pl.milej.michal.wordofreaders.user;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserPrincipalUtils {

    public static Long getUserPrincipalId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
