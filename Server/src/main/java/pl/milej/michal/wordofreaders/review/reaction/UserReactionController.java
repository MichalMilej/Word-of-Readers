package pl.milej.michal.wordofreaders.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.milej.michal.wordofreaders.review.ReviewServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/reviews/{reviewId}/user-reactions")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@Transactional
public class UserReactionController {
    final UserReactionServiceImpl userReactionService;
    final ReviewServiceImpl reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Map<String, Object> addUserReaction(@PathVariable final Long reviewId,
                                        @RequestBody final UserReactionRequest userReactionRequest) {
        return reviewService.addUserReaction(reviewId, userReactionRequest);
    }

    @GetMapping("/{userId}")
    UserReactionResponse getUserReaction(@PathVariable final Long reviewId, @PathVariable final Long userId) {
        return userReactionService.getUserReaction(reviewId, userId);
    }

    @PatchMapping("/{userId}")
    Map<String, Object> updateUserReaction(@PathVariable final Long reviewId,
                                           @PathVariable final Long userId,
                                           @RequestBody final UserReactionRequest userReactionRequest) {
        return reviewService.updateUserReaction(reviewId, userId, userReactionRequest);
    }
}
