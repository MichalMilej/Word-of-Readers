package pl.milej.michal.wordofreaders.book.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewAuthenticationService {

    final ReviewService reviewService;

    public boolean isUserIdInReviewEqualsPrincipalId(final Long reviewId, final long principalId) {
        return reviewService.findReviewById(reviewId).getUser().getId() == principalId;
    }
}
