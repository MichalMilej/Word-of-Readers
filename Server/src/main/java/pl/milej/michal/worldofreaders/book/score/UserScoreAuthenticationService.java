package pl.milej.michal.worldofreaders.book.score;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.milej.michal.worldofreaders.user.UserPrincipalUtils;
import pl.milej.michal.worldofreaders.user.UserRole;

@Component
@RequiredArgsConstructor
public class UserScoreAuthenticationService {
    final UserScoreServiceImpl userScoreService;

    public boolean canUserAccessUserScore(final long userScoreId) {
        final long principalId = UserPrincipalUtils.getUserPrincipalId();
        final UserRole userRole = UserPrincipalUtils.getUserPrincipalRole();

        if (userRole == UserRole.MOD || userRole == UserRole.ADMIN) {
            return true;
        }
        final UserScore userScore = userScoreService.findUserScoreById(userScoreId);
        return principalId == userScore.getUser().getId();
    }
}
