package pl.milej.michal.worldofreaders.book.review;

import pl.milej.michal.worldofreaders.user.UserConverter;

public class ReviewConverter {

    public static ReviewResponse convertToReviewResponse(final Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .text(review.getText())
                .publicationDate(review.getPublicationDate())
                .likes(review.getLikes())
                .dislikes(review.getDislikes())
                .bookId(review.getBook().getId())
                .userResponsePublic(UserConverter.convertToUserResponsePublic(review.getUser()))
                .build();
    }
}
