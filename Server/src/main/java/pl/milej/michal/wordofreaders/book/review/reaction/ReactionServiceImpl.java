package pl.milej.michal.wordofreaders.book.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.milej.michal.wordofreaders.book.review.Review;
import pl.milej.michal.wordofreaders.book.review.ReviewResponse;
import pl.milej.michal.wordofreaders.book.review.ReviewServiceImpl;
import pl.milej.michal.wordofreaders.exception.BadRequestException;
import pl.milej.michal.wordofreaders.user.UserPrincipalUtils;
import pl.milej.michal.wordofreaders.user.UserServiceImpl;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    final ReactionRepository reactionRepository;
    final UserServiceImpl userService;
    final ReviewServiceImpl reviewService;
    final ReactionRequestValidator reactionRequestValidator;

    @Override
    public Map<String, Object> addUserReaction(final long reviewId, final ReactionRequest reactionRequest) {
        final long userId = UserPrincipalUtils.getUserPrincipalId();
        final UserReaction userReaction = reactionRequest.getUserReaction();

        reactionRequestValidator.validateReviewExists(reviewId);
        reactionRequestValidator.validateNoUserReactionAtReview(reviewId, userId);

        Reaction newReaction = new Reaction();
        newReaction.setUserReaction(userReaction);
        newReaction.setUser(userService.findUserById(userId));
        newReaction.setReview(reviewService.findReviewById(reviewId));
        final Reaction savedReaction = reactionRepository.save(newReaction);

        final Review review = reviewService.findReviewById(reviewId);
        final ReviewResponse reviewResponse = userReaction == UserReaction.LIKE ?
                reviewService.updateLikes(review.getId(), review.getLikes() + 1) :
                reviewService.updateDislikes(review.getId(), review.getDislikes() + 1);

        return Map.of(
                "UserReactionResponse", ReactionConverter.convertToUserReactionResponse(savedReaction),
                "ReviewResponse", reviewResponse);
    }

    @Override
    public ReactionResponse getUserReaction(final long reactionId) {
        return ReactionConverter.convertToUserReactionResponse(findReactionById(reactionId));
    }

    @Override
    public ReactionResponse getUserReactionByReviewIdAndUserId(final Long reviewId, final Long userId) {
        // Check that user and review exist
        userService.findUserById(userId);
        reviewService.findReviewById(reviewId);
        // Check if user posted reaction
        try {
            return ReactionConverter.convertToUserReactionResponse(findReactionByReviewIdAndUserId(reviewId, userId));
        } catch (ResourceNotFoundException e) {
            return ReactionResponse.builder()
                    .reviewId(reviewId)
                    .userId(userId)
                    .userReaction(UserReaction.NONE).build();
        }
    }

    @Override
    public Map<String, Object> updateUserReaction(final long reactionId, final ReactionRequest reactionRequest) {
        final Reaction existingReaction = findReactionById(reactionId);
        final UserReaction lastUserReaction = existingReaction.getUserReaction();
        final UserReaction newUserReaction = reactionRequest.getUserReaction();
        if (lastUserReaction == newUserReaction) {
            throw new BadRequestException("Passed reaction is the same as it was");
        }
        existingReaction.setUserReaction(reactionRequest.getUserReaction());
        final Reaction updatedReaction = reactionRepository.save(existingReaction);

        final Review review = reviewService.findReviewById(existingReaction.getReview().getId());

        if (lastUserReaction == UserReaction.LIKE) {
            reviewService.updateLikes(review.getId(), review.getLikes() - 1);
        } else if (lastUserReaction == UserReaction.DISLIKE) {
            reviewService.updateDislikes(review.getId(), review.getDislikes() - 1);
        }

        final ReviewResponse reviewResponse;
        if (newUserReaction == UserReaction.LIKE) {
            reviewResponse = reviewService.updateLikes(review.getId(), review.getLikes() + 1);
        } else  if (newUserReaction == UserReaction.DISLIKE) {
            reviewResponse = reviewService.updateDislikes(review.getId(), review.getDislikes() + 1);
        } else {
            reviewResponse = reviewService.getReview(review.getId());
        }

        return Map.of(
                "UserReactionResponse", ReactionConverter.convertToUserReactionResponse(updatedReaction),
                "ReviewResponse", reviewResponse);
    }

    public Reaction findReactionById(final long reactionId) {
        return reactionRepository.findById(reactionId).orElseThrow(() -> {
            throw new ResourceNotFoundException("UserReaction not found");
        });
    }

    public Reaction findReactionByReviewIdAndUserId(final long reviewId, final long userId) {
        return reactionRepository.findByReviewIdAndUserId(reviewId, userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("User reaction not found");
        });
    }
}
