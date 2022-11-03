package pl.milej.michal.wordofreaders.review.reaction;

public interface UserReactionService {

    UserReactionResponse addUserReaction(final UserReactionRequest userReactionRequest);
    UserReactionResponse updateUserReaction(final UserReactionRequest userReactionRequest);
    UserReactionResponse getUserReaction(final Long reviewId, final Long userId);
    boolean hasUserPostedReaction(Long userId, Long reviewId);
}
