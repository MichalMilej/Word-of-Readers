package pl.milej.michal.wordofreaders.review;

import org.springframework.data.domain.Page;

public interface ReviewService {
    ReviewResponse addReview(final ReviewRequest reviewRequest);
    Page<ReviewResponse> getReviewsByBookId(final Long bookId, final Integer pageNumber, final Integer pageSize);
    void deleteReview(final Long reviewId);
}
