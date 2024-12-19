package pl.milej.michal.worldofreaders.book.review.reaction;


public class ReactionConverter {

    public static ReactionResponse convertToUserReactionResponse(final Reaction reaction) {
        return ReactionResponse.builder()
                .id(reaction.getId())
                .userReaction(reaction.getUserReaction())
                .userId(reaction.getUser().getId())
                .reviewId(reaction.getReview().getId())
                .build();
    }
}
