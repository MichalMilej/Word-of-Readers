package pl.milej.michal.worldofreaders.book.review.reaction;

import java.util.Map;

public interface ReactionService {

    Map<String, Object> addUserReaction(final long reviewId, final ReactionRequest reactionRequest);
    ReactionResponse getUserReaction(final long reactionId);
    ReactionResponse getUserReactionByReviewIdAndUserId(final Long reviewId, final Long userId);
    Map<String, Object> updateUserReaction(final long reactionId, final ReactionRequest reactionRequest);
}
