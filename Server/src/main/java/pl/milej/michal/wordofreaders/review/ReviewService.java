package pl.milej.michal.wordofreaders.review;

import org.springframework.data.domain.Page;
import pl.milej.michal.wordofreaders.review.reaction.Reaction;
import pl.milej.michal.wordofreaders.review.reaction.UserReactionRequest;

import java.util.Map;

public interface ReviewService {
    ReviewResponse addReview(final ReviewRequest reviewRequest);
    Page<ReviewResponse> getReviewsByBookId(final Long bookId, final Integer pageNumber, final Integer pageSize);
    ReviewResponse getReview(final Long id);
    Map<String, Object> addUserReaction(final Long reviewId, final UserReactionRequest userReactionRequest);
    Map<String, Object> updateUserReaction(final Long reviewId, final Long userId, final UserReactionRequest userReactionRequest);
    void deleteReview(final Long reviewId);
}
