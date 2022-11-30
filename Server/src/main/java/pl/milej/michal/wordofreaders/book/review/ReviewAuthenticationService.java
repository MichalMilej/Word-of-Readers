package pl.milej.michal.wordofreaders.book.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.milej.michal.wordofreaders.user.UserPrincipalUtils;

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
