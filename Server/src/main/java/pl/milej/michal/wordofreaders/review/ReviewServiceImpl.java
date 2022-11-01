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
import pl.milej.michal.wordofreaders.user.UserRepository;

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
    public void deleteReview(final Long reviewId) {
         reviewRepository.deleteById(reviewId);
    }
}
