package pl.milej.michal.wordofreaders.review;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ReviewRequest {

    @NotNull
    private String text;
    private Long bookId;
}
