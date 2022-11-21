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
        reactionRequestValidator.validateNoUserReactionAtReview(userId, reviewId);

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
        return ReactionConverter.convertToUserReactionResponse(findUserReactionById(reactionId));
    }

    @Override
    public ReactionResponse getUserReactionByReviewIdAndUserId(final Long reviewId, final Long userId) {
        return ReactionConverter.convertToUserReactionResponse(
                reactionRepository.findByReviewIdAndUserId(reviewId, userId).orElseThrow(() -> {
                    throw new ResourceNotFoundException("User reaction not found");
                }));
    }

    @Override
    public Map<String, Object> updateUserReaction(final long reactionId, final ReactionRequest reactionRequest) {
        final Reaction existingReaction = findUserReactionById(reactionId);
        final UserReaction lastUserReaction = existingReaction.getUserReaction();
        final UserReaction newUserReaction = reactionRequest.getUserReaction();
        if (lastUserReaction == newUserReaction) {
            throw new BadRequestException("Passed reaction is the same as it was");
        }
        existingReaction.setUserReaction(reactionRequest.getUserReaction());
        final Reaction updatedReaction = reactionRepository.save(existingReaction);

        final Review review = reviewService.findReviewById(existingReaction.getReview().getId());
        ReviewResponse reviewResponse;
        if (lastUserReaction == UserReaction.LIKE) {
            reviewService.updateLikes(review.getId(), review.getLikes() - 1);
            reviewResponse = reviewService.updateDislikes(review.getId(), review.getDislikes() + 1);
        } else {
            reviewService.updateLikes(review.getId(), review.getLikes() + 1);
            reviewResponse = reviewService.updateDislikes(review.getId(), review.getDislikes() - 1);
        }

        return Map.of(
                "UserReactionResponse", ReactionConverter.convertToUserReactionResponse(updatedReaction),
                "ReviewResponse", reviewResponse);
    }

    public Reaction findUserReactionById(final long reactionId) {
        return reactionRepository.findById(reactionId).orElseThrow(() -> {
            throw new ResourceNotFoundException("UserReaction not found");
        });
    }
}
