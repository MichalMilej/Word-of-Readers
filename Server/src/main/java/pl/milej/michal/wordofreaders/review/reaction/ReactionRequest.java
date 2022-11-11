package pl.milej.michal.wordofreaders.review.reaction;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReactionRequest {

    @NotNull
    private UserReaction userReaction;
}
