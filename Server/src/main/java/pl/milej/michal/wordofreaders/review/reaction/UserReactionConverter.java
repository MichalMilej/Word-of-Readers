package pl.milej.michal.wordofreaders.review.reaction;


public class UserReactionConverter {

    public static UserReactionResponse convertUserReactionToUserReactionResponse(final UserReaction userReaction) {
        return UserReactionResponse.builder()
                .id(userReaction.getId())
                .reaction(userReaction.getReaction())
                .userId(userReaction.getUser().getId())
                .reviewId(userReaction.getReview().getId())
                .build();
    }
}
