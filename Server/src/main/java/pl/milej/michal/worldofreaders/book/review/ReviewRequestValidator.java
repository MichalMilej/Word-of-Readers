package pl.milej.michal.worldofreaders.book.review;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.milej.michal.worldofreaders.exception.BadRequestException;

@Component
public class ReviewRequestValidator {

    public void validateReviewText(final String text) {
        if (!StringUtils.hasText(text)) {
            throw new BadRequestException("Review text is empty");
        }
    }
}
