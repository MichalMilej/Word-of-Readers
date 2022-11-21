package pl.milej.michal.wordofreaders.book.review;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.BadRequestException;

@Component
public class ReviewRequestValidator {

    public void validateReview(final ReviewRequest reviewRequest) {
        validateReviewText(reviewRequest);
        if (reviewRequest.getBookId() == null) {
            throw new BadRequestException("No book id specified");
        }
    }

    public void validateReviewText(final ReviewRequest reviewRequest) {
        if (!StringUtils.hasText(reviewRequest.getText())) {
            throw new BadRequestException("Review text is empty");
        }
    }
}
