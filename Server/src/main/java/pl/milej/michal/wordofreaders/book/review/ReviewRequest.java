package pl.milej.michal.wordofreaders.book.review;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReviewRequest {

    @NotNull
    private String text;
    private Long bookId;
}
