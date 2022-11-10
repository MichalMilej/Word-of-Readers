package pl.milej.michal.wordofreaders.review.reaction;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserReactionRequest {

    @NotNull
    private Reaction reaction;
    private Long reviewId;
    private Long userId;
}
