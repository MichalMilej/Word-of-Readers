package pl.milej.michal.wordofreaders.review;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.milej.michal.wordofreaders.book.BookRepository;
import pl.milej.michal.wordofreaders.user.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRequestValidator reviewRequestValidator;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Override
    public ReviewRequest addReview(ReviewRequest reviewRequest) {
        reviewRequestValidator.validateReview(reviewRequest);

        Review newReview = new Review();
        newReview.setText(reviewRequest.getText());
        newReview.setUser(userRepository.findById(reviewRequest.getUserId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("User not found");
        }));
        newReview.setBook(bookRepository.findById(reviewRequest.getBookId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        }));

        // TO DO

        return null;
    }
}
