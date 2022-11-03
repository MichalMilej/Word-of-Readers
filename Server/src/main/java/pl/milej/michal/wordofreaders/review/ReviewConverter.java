package pl.milej.michal.wordofreaders.review;

public class ReviewConverter {

    public static ReviewResponse convertReviewToReviewResponse(final Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .text(review.getText())
                .publicationDate(review.getPublicationDate())
                .likes(review.getLikes())
                .dislikes(review.getDislikes())
                .bookId(review.getBook().getId())
                .userId(review.getUser().getId())
                .build();
    }
}
