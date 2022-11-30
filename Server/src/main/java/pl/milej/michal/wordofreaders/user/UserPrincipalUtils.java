package pl.milej.michal.wordofreaders.user;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserPrincipalUtils {

    public static Long getUserPrincipalId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static String getUserPrincipalUsername() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static UserRole getUserPrincipalRole() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserRole();
    }

    public static boolean hasPrincipalModOrAdminRole() {
        final UserRole principalRole = getUserPrincipalRole();
        return principalRole == UserRole.MOD || principalRole == UserRole.ADMIN;
    }
}
