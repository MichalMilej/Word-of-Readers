package pl.milej.michal.wordofreaders.book.score;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.milej.michal.wordofreaders.book.BookServiceImpl;
import pl.milej.michal.wordofreaders.exception.BadRequestException;
import pl.milej.michal.wordofreaders.exception.LimitExceededException;
import pl.milej.michal.wordofreaders.user.UserServiceImpl;

@Component
@RequiredArgsConstructor
public class UserScoreRequestValidator {

    final UserScoreRepository userScoreRepository;
    final UserServiceImpl userService;
    final BookServiceImpl bookService;

    public void validateUserScoreRequest(final UserScoreRequest userScoreRequest) {
        validateScore(userScoreRequest.getScore());
        bookService.findBookById(userScoreRequest.getBookId());
        userService.findUserById(userScoreRequest.getUserId());
    }

    public void validateScore(final int score) {
        if (score < 1 || score > 10) {
            throw new BadRequestException("Wrong score. Correct is from 1 to 10");
        }
    }
    public void validateUserScoreNotAddedYet(final UserScoreRequest userScoreRequest) {
        if (userScoreRepository.findByBookIdAndUserId(userScoreRequest.getBookId(), userScoreRequest.getUserId()).isPresent()) {
            throw new LimitExceededException("User has already added the score");
        }
    }
}
