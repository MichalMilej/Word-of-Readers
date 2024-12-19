package pl.milej.michal.worldofreaders.book.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.milej.michal.worldofreaders.user.UserPrincipalUtils;

@Service
@RequiredArgsConstructor
public class ReviewAuthenticationService {

    final ReviewService reviewService;

    public boolean canPrincipalAccessReview(final Long reviewId, final long principalId) {
        if (UserPrincipalUtils.hasPrincipalModOrAdminRole()) {
            return true;
        }
        return reviewService.findReviewById(reviewId).getUser().getId() == principalId;
    }
}
