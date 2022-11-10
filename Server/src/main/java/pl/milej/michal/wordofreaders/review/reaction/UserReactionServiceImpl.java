package pl.milej.michal.wordofreaders.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.milej.michal.wordofreaders.review.ReviewRepository;
import pl.milej.michal.wordofreaders.user.UserRepository;


@Service
@RequiredArgsConstructor
public class UserReactionServiceImpl implements UserReactionService {

    final UserReactionRepository userReactionRepository;
    final UserRepository userRepository;
    final ReviewRepository reviewRepository;

    @Override
    public UserReactionResponse addUserReaction(final UserReactionRequest userReactionRequest) {
        UserReaction newUserReaction = new UserReaction();
        newUserReaction.setReaction(userReactionRequest.getReaction());
        newUserReaction.setUser(userRepository.findById(userReactionRequest.getUserId()).get());
        newUserReaction.setReview(reviewRepository.findById(userReactionRequest.getReviewId()).get());
        return UserReactionConverter.convertUserReactionToUserReactionResponse(userReactionRepository.save(newUserReaction));
    }

    @Override
    public UserReactionResponse updateUserReaction(final UserReactionRequest userReactionRequest) {
        final UserReaction existingUserReaction = userReactionRepository.
                findByUserIdAndReviewId(userReactionRequest.getUserId(), userReactionRequest.getReviewId()).get();
        existingUserReaction.setReaction(userReactionRequest.getReaction());

        return UserReactionConverter.convertUserReactionToUserReactionResponse(userReactionRepository.save(existingUserReaction));
    }

    @Override
    public UserReactionResponse getUserReaction(final Long reviewId, final Long userId) {
        return UserReactionConverter.convertUserReactionToUserReactionResponse(
                userReactionRepository.findByUserIdAndReviewId(userId, reviewId).orElseThrow(() -> {
            throw new ResourceNotFoundException("User reaction not found");
        }));
    }

    @Override
    public boolean hasUserPostedReaction(final Long userId, final Long reviewId) {
        return userReactionRepository.findByUserIdAndReviewId(userId, reviewId).isPresent();
    }
}
