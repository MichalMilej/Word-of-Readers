package pl.milej.michal.wordofreaders.book.review;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.BadRequestException;

@Component
public class ReviewRequestValidator {

    public void validateReviewText(final String text) {
        if (!StringUtils.hasText(text)) {
            throw new BadRequestException("Review text is empty");
        }
    }
}
