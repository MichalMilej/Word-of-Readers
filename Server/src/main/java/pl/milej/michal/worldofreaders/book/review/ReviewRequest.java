package pl.milej.michal.worldofreaders.book.review;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReviewRequest {

    @NotNull
    private String text;
}
