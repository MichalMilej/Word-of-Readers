package pl.milej.michal.worldofreaders.book.review.reaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReactionResponse {

    private Long id;
    private UserReaction userReaction;
    private Long reviewId;
    private Long userId;
}
