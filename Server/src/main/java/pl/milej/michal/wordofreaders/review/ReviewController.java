package pl.milej.michal.wordofreaders.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@Transactional
public class ReviewController {

    final ReviewServiceImpl reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ReviewResponse addReview(@RequestBody final ReviewRequest reviewRequest) {
        return reviewService.addReview(reviewRequest);
    }

    @GetMapping("/book/{bookId}")
    @Transactional(readOnly = true)
    Page<ReviewResponse> getReviewsByBookId(@PathVariable final Long bookId,
                                    @RequestParam final Integer pageNumber,
                                    @RequestParam final Integer pageSize) {
        return reviewService.getReviewsByBookId(bookId, pageNumber, pageSize);
    }

    @GetMapping("/{reviewId}")
    ReviewResponse getReview(@PathVariable final Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    @DeleteMapping("/{reviewId}")
    void deleteReview(@PathVariable final Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
