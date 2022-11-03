package pl.milej.michal.wordofreaders.review.reaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReactionResponse {

    private Long id;
    private Reaction reaction;
    private Long reviewId;
    private Long userId;
}
