package pl.milej.michal.wordofreaders.book.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/books/reviews")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@Transactional
public class ReactionController {
    final ReactionServiceImpl userReactionService;
    final ReactionAuthenticationService reactionAuthenticationService;

    @PostMapping("/{reviewId}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    Map<String, Object> addUserReaction(@PathVariable final Long reviewId,
                                        @RequestBody final ReactionRequest reactionRequest) {
        return userReactionService.addUserReaction(reviewId, reactionRequest);
    }

    @GetMapping("/reactions/{reactionId}")
    ReactionResponse getUserReaction(@PathVariable long reactionId) {
        return userReactionService.getUserReaction(reactionId);
    }

    @GetMapping("{reviewId}/reactions/user/{userId}")
    ReactionResponse getUserReactionByUserIdAndReviewId(@PathVariable final Long reviewId, @PathVariable final Long userId) {
        return userReactionService.getUserReactionByReviewIdAndUserId(reviewId, userId);
    }

    @PatchMapping("/reactions/{reactionId}")
    @PreAuthorize("@reactionAuthenticationService.isUserIdInReactionIdEqualsPrincipalId(#reactionId, principal.id)")
    Map<String, Object> updateUserReaction(@PathVariable final Long reactionId,
                                           @RequestBody final ReactionRequest reactionRequest) {
        return userReactionService.updateUserReaction(reactionId, reactionRequest);
    }
}
