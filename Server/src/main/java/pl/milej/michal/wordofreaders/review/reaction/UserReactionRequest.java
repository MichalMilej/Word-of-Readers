package pl.milej.michal.wordofreaders.review.reaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReactionRequest {

    private Reaction reaction;
    private Long reviewId;
    private Long userId;
}
