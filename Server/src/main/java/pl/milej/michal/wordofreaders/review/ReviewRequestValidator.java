package pl.milej.michal.wordofreaders.review;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.BadRequestException;

@Component
public class ReviewRequestValidator {

    public void validateReview(final ReviewRequest reviewRequest) {
        if (!StringUtils.hasText(reviewRequest.getText())) {
            throw new BadRequestException("Review text is empty");
        }
        if (reviewRequest.getBookId() == null) {
            throw new BadRequestException("No book id specified");
        }
        if (reviewRequest.getUserId() == null) {
            throw new BadRequestException("No user id specified");
        }
    }
}
