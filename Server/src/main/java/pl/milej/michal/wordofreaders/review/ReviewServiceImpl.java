package pl.milej.michal.wordofreaders.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.milej.michal.wordofreaders.book.Book;
import pl.milej.michal.wordofreaders.book.BookRepository;
import pl.milej.michal.wordofreaders.exception.BadRequestException;
import pl.milej.michal.wordofreaders.exception.LimitExceededException;
import pl.milej.michal.wordofreaders.review.reaction.*;
import pl.milej.michal.wordofreaders.user.UserRepository;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    final private ReviewRequestValidator reviewRequestValidator;
    final private UserReactionRequestValidator userReactionRequestValidator;
    final private UserRepository userRepository;
    final private BookRepository bookRepository;
    final private ReviewRepository reviewRepository;

    final private UserReactionService userReactionService;

    @Override
    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        reviewRequestValidator.validateReview(reviewRequest);

        Review newReview = new Review();
        newReview.setText(reviewRequest.getText());
        newReview.setUser(userRepository.findById(reviewRequest.getUserId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("User not found");
        }));
        newReview.setBook(bookRepository.findById(reviewRequest.getBookId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        }));
        newReview.setPublicationDate(new Date(System.currentTimeMillis()));

        final Review savedReview = reviewRepository.save(newReview);
        log.info("User with id: {} added review with id: {}", reviewRequest.getUserId(), savedReview.getId());

        return ReviewConverter.convertReviewToReviewResponse(savedReview);
    }

    @Override
    public Page<ReviewResponse> getReviewsByBookId(final Long bookId, final Integer pageNumber, final Integer pageSize) {
        final Book book = bookRepository.findById(bookId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        });
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reviewRepository.findByBookId(book.getId(), pageable).map(ReviewConverter::convertReviewToReviewResponse);
    }

    @Override
    public ReviewResponse getReview(final Long reviewId) {
        return ReviewConverter.convertReviewToReviewResponse(getReviewById(reviewId));
    }

    @Override
    public Map<String, Object> addUserReaction(final Long reviewId, final UserReactionRequest userReactionRequest) {
        userReactionRequestValidator.validateUserReactionRequest(userReactionRequest);
        validateReviewId(reviewId, userReactionRequest);
        if (userReactionService.hasUserPostedReaction(userReactionRequest.getUserId(), userReactionRequest.getReviewId())) {
            throw new LimitExceededException("User has already posted the reaction on this review");
        }

        final Review review = reviewRepository.findById(reviewId).get();
        addLikesOrDislikesToReview(review, userReactionRequest.getReaction(), 1);

        Map<String, Object> map = new HashMap<>();
        map.put("UserReactionResponse", userReactionService.addUserReaction(userReactionRequest));
        map.put("ReviewResponse", ReviewConverter.convertReviewToReviewResponse(reviewRepository.save(review)));
        return map;
    }

    @Override
    public Map<String, Object> updateUserReaction(final Long reviewId, final Long userId, final UserReactionRequest userReactionRequest) {
        userReactionRequestValidator.validateUserReactionRequest(userReactionRequest);
        validateReviewId(reviewId, userReactionRequest);
        validateUserId(userId, userReactionRequest);
        if (!userReactionService.hasUserPostedReaction(userReactionRequest.getUserId(), userReactionRequest.getReviewId())) {
            throw new ResourceNotFoundException("User has not posted the reaction on this review yet");
        }

        final UserReactionResponse userReactionResponse = userReactionService.getUserReaction(reviewId, userId);

        final Review review = reviewRepository.findById(reviewId).get();
        addLikesOrDislikesToReview(review, userReactionResponse.getReaction(), -1);
        addLikesOrDislikesToReview(review, userReactionRequest.getReaction(), 1);

        Map<String, Object> map = new HashMap<>();
        map.put("UserReactionResponse", userReactionService.updateUserReaction(userReactionRequest));
        map.put("ReviewResponse", ReviewConverter.convertReviewToReviewResponse(reviewRepository.save(review)));
        return map;
    }

    @Override
    public void deleteReview(final Long reviewId) {
         reviewRepository.deleteById(reviewId);
    }

    private Review getReviewById(final Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Review not found");
        });
    }

    private void validateReviewId(final Long reviewId, final UserReactionRequest userReactionRequest) {
        if (!Objects.equals(reviewId, userReactionRequest.getReviewId())) {
            throw new BadRequestException("Variable reviewId in path is different than in body");
        }
    }

    private void validateUserId(final Long userId, final UserReactionRequest userReactionRequest) {
        if (!Objects.equals(userId, userReactionRequest.getUserId())) {
            throw new BadRequestException("Variable userId in path is different than in body");
        }
    }

    private void addLikesOrDislikesToReview(final Review review, final Reaction reaction, final int value) {
        if (reaction == Reaction.LIKE) {
            review.setLikes(review.getLikes() + value);
        } else if (reaction == Reaction.DISLIKE) {
            review.setDislikes(review.getDislikes() + value);
        }
    }
}
