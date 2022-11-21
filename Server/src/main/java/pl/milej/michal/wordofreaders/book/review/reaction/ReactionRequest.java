package pl.milej.michal.wordofreaders.book.review.reaction;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReactionRequest {

    @NotNull
    private UserReaction userReaction;
}
