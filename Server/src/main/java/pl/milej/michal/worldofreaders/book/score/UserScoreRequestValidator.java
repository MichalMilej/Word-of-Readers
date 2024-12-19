package pl.milej.michal.worldofreaders.book.score;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.milej.michal.worldofreaders.book.BookServiceImpl;
import pl.milej.michal.worldofreaders.exception.BadRequestException;
import pl.milej.michal.worldofreaders.exception.LimitExceededException;
import pl.milej.michal.worldofreaders.user.UserServiceImpl;

@Component
@RequiredArgsConstructor
public class UserScoreRequestValidator {

    final UserScoreRepository userScoreRepository;
    final UserServiceImpl userService;
    final BookServiceImpl bookService;

    public void validateUserScoreRequest(final UserScoreRequest userScoreRequest) {
        validateScore(userScoreRequest.getScore());
    }

    public void validateBookId(final long bookId) {
        bookService.findBookById(bookId);
    }

    public void validateScore(final int score) {
        if (score < 1 || score > 10) {
            throw new BadRequestException("Wrong score. Correct is from 1 to 10");
        }
    }
    public void validateUserScoreNotAddedYet(final long bookId, final long userId) {
        if (userScoreRepository.findByBookIdAndUserId(bookId, userId).isPresent()) {
            throw new LimitExceededException("User has already added the score");
        }
    }
}
