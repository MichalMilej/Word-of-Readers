package pl.milej.michal.worldofreaders.book.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@Transactional
public class ReviewController {

    final ReviewServiceImpl reviewService;
    final ReviewAuthenticationService reviewAuthenticationService;

    @PostMapping("/{bookId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    ReviewResponse addReview(@PathVariable final long bookId, @RequestBody final ReviewRequest reviewRequest) {
        return reviewService.addReview(bookId, reviewRequest);
    }

    @GetMapping("/{bookId}/reviews")
    @Transactional(readOnly = true)
    Page<ReviewResponse> getReviewsByBookId(@PathVariable final Long bookId,
                                    @RequestParam final Integer pageNumber,
                                    @RequestParam final Integer pageSize) {
        return reviewService.getReviewsByBookId(bookId, pageNumber, pageSize);
    }

    @GetMapping("/reviews/{reviewId}")
    ReviewResponse getReview(@PathVariable final Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    @PatchMapping ("/reviews/{reviewId}")
    @PreAuthorize("@reviewAuthenticationService.canPrincipalAccessReview(#reviewId, principal.id)")
    ReviewResponse editReview(@PathVariable final long reviewId, @RequestBody final ReviewRequest reviewRequest) {
        return reviewService.editReview(reviewId, reviewRequest);
    }

    @DeleteMapping("/reviews/{reviewId}")
    @PreAuthorize("@reviewAuthenticationService.canPrincipalAccessReview(#reviewId, principal.id)")
    void deleteReview(@PathVariable final Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
