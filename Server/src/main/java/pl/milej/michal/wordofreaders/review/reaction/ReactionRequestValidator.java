package pl.milej.michal.wordofreaders.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.milej.michal.wordofreaders.exception.LimitExceededException;
import pl.milej.michal.wordofreaders.review.ReviewServiceImpl;

@Component
@RequiredArgsConstructor
public class ReactionRequestValidator {

    final ReactionRepository reactionRepository;
    final ReviewServiceImpl reviewService;

    public void validateReviewExists(final long reviewId) {
        reviewService.findReviewById(reviewId);
    }

    public void validateNoUserReactionAtReview(final long userId, final long reviewId) {
        if (reactionRepository.findByReviewIdAndUserId(userId, reviewId).isPresent()) {
            throw new LimitExceededException("User posted reaction at this review");
        }
    }
}
