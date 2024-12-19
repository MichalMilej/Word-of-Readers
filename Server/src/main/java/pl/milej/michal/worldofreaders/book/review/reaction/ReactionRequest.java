package pl.milej.michal.worldofreaders.book.review.reaction;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReactionRequest {

    @NotNull
    private UserReaction userReaction;
}
