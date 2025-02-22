package pl.milej.michal.worldofreaders.book.review;

import org.springframework.data.domain.Page;

public interface ReviewService {
    ReviewResponse addReview(final long bookId, final ReviewRequest reviewRequest);
    Page<ReviewResponse> getReviewsByBookId(final Long bookId, final Integer pageNumber, final Integer pageSize);
    ReviewResponse getReview(final Long id);
    Review findReviewById(final Long reviewId);
    ReviewResponse updateLikes(final long reviewId, final int likes);
    ReviewResponse updateDislikes(final long reviewId, final int dislikes);
    ReviewResponse editReview(final long reviewId, final ReviewRequest reviewRequest);
    void deleteReview(final Long reviewId);
}
