package pl.milej.michal.worldofreaders.book.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.milej.michal.worldofreaders.book.review.ReviewServiceImpl;
import pl.milej.michal.worldofreaders.exception.LimitExceededException;

@Component
@RequiredArgsConstructor
public class ReactionRequestValidator {

    final ReactionRepository reactionRepository;
    final ReviewServiceImpl reviewService;

    public void validateReviewExists(final long reviewId) {
        reviewService.findReviewById(reviewId);
    }

    public void validateNoUserReactionAtReview(final long reviewId, final long userId) {
        if (reactionRepository.findByReviewIdAndUserId(reviewId, userId).isPresent()) {
            throw new LimitExceededException("User posted reaction at this review");
        }
    }
}
