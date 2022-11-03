package pl.milej.michal.wordofreaders.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import pl.milej.michal.wordofreaders.exception.BadRequestException;
import pl.milej.michal.wordofreaders.exception.LimitExceededException;
import pl.milej.michal.wordofreaders.review.ReviewRepository;
import pl.milej.michal.wordofreaders.user.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReactionRequestValidator {

    final UserReactionRepository userReactionRepository;
    final UserRepository userRepository;
    final ReviewRepository reviewRepository;

    public void validateUserReactionRequest(final UserReactionRequest userReactionRequest) {
        validateNotNull(userReactionRequest);
        validateEntitiesExists(userReactionRequest);
    }

    private void validateNotNull(final UserReactionRequest userReactionRequest) {
        if (userReactionRequest.getReaction() == null) {
            throw new BadRequestException("Reaction is empty");
        }
        if (userReactionRequest.getUserId() == null) {
            throw new BadRequestException("UserId is empty");
        }
        if (userReactionRequest.getReviewId() == null) {
            throw new BadRequestException("reviewId is empty");
        }
    }

    private void validateEntitiesExists(final UserReactionRequest userReactionRequest) {
        if (userRepository.findById(userReactionRequest.getUserId()).isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        if (reviewRepository.findById(userReactionRequest.getReviewId()).isEmpty()) {
            throw new ResourceNotFoundException("Review not found");
        }
    }
}
