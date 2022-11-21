package pl.milej.michal.wordofreaders.book.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.milej.michal.wordofreaders.book.Book;
import pl.milej.michal.wordofreaders.book.BookRepository;
import pl.milej.michal.wordofreaders.book.review.reaction.UserReaction;
import pl.milej.michal.wordofreaders.exception.BadServerRequestException;
import pl.milej.michal.wordofreaders.user.UserRepository;
import pl.milej.michal.wordofreaders.user.UserPrincipalUtils;

import java.sql.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    final private ReviewRequestValidator reviewRequestValidator;
    final private UserRepository userRepository;
    final private BookRepository bookRepository;
    final private ReviewRepository reviewRepository;

    @Override
    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        reviewRequestValidator.validateReview(reviewRequest);

        long userId = UserPrincipalUtils.getUserPrincipalId();

        Review newReview = new Review();
        newReview.setText(reviewRequest.getText());
        newReview.setUser(userRepository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("User not found");
        }));
        newReview.setBook(bookRepository.findById(reviewRequest.getBookId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        }));
        newReview.setPublicationDate(new Date(System.currentTimeMillis()));

        final Review savedReview = reviewRepository.save(newReview);
        log.info("User with id: {} added review with id: {}", userId, savedReview.getId());

        return ReviewConverter.convertToReviewResponse(savedReview);
    }

    @Override
    public Page<ReviewResponse> getReviewsByBookId(final Long bookId, final Integer pageNumber, final Integer pageSize) {
        final Book book = bookRepository.findById(bookId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        });
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reviewRepository.findByBookId(book.getId(), pageable).map(ReviewConverter::convertToReviewResponse);
    }

    @Override
    public ReviewResponse getReview(final Long reviewId) {
        return ReviewConverter.convertToReviewResponse(findReviewById(reviewId));
    }

    @Override
    public ReviewResponse updateLikes(long reviewId, int likes) {
        if (likes < 0) {
            throw new BadServerRequestException("Like number can not be lesser than 0");
        }
        final Review review = findReviewById(reviewId);
        review.setLikes(likes);
        return ReviewConverter.convertToReviewResponse(reviewRepository.save(review));
    }

    @Override
    public ReviewResponse updateDislikes(long reviewId, int dislikes) {
        if (dislikes < 0) {
            throw new BadServerRequestException("Dislike number can not be lesser than 0");
        }
        final Review review = findReviewById(reviewId);
        review.setDislikes(dislikes);
        return ReviewConverter.convertToReviewResponse(reviewRepository.saveAndFlush(review));
    }

    @Override
    public ReviewResponse editReview(final long reviewId, final ReviewRequest reviewRequest) {
        reviewRequestValidator.validateReviewText(reviewRequest);
        final Review review = findReviewById(reviewId);
        review.setText(reviewRequest.getText());
        return ReviewConverter.convertToReviewResponse(reviewRepository.saveAndFlush(review));
    }

    @Override
    public void deleteReview(final Long reviewId) {
         reviewRepository.deleteById(reviewId);
    }

    public Review findReviewById(final Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Review not found");
        });
    }

    private void addLikesOrDislikesToReview(final Review review, final UserReaction userReaction, final int value) {
        if (userReaction == UserReaction.LIKE) {
            review.setLikes(review.getLikes() + value);
        } else if (userReaction == UserReaction.DISLIKE) {
            review.setDislikes(review.getDislikes() + value);
        }
    }
}
