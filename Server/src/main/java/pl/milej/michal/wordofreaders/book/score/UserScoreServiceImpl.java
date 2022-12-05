package pl.milej.michal.wordofreaders.book.score;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.milej.michal.wordofreaders.book.Book;
import pl.milej.michal.wordofreaders.book.BookResponse;
import pl.milej.michal.wordofreaders.book.BookServiceImpl;
import pl.milej.michal.wordofreaders.user.UserPrincipalUtils;
import pl.milej.michal.wordofreaders.user.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserScoreServiceImpl implements UserScoreService {

    final UserScoreRepository userScoreRepository;
    final BookServiceImpl bookService;
    final UserServiceImpl userService;
    final UserScoreRequestValidator userScoreRequestValidator;

    @Override
    public Map<String, Object> addUserScore(final long bookId, final UserScoreRequest userScoreRequest) {
        final long userId = UserPrincipalUtils.getUserPrincipalId();

        userScoreRequestValidator.validateUserScoreRequest(userScoreRequest);
        userScoreRequestValidator.validateBookId(bookId);
        userScoreRequestValidator.validateUserScoreNotAddedYet(bookId, userId);

        UserScore newUserScore = new UserScore();
        newUserScore.setScore(userScoreRequest.getScore());
        newUserScore.setUser(userService.findUserById(userId));
        newUserScore.setBook(bookService.findBookById(bookId));

        final UserScoreResponse newUserScoreResponse = UserScoreConverter.convertUserScoreToUserScoreResponse(
                userScoreRepository.saveAndFlush(newUserScore));

        bookService.incrementUserScoreCount(bookId, 1);
        final BookResponse bookResponse = updateUserScoreAverageInBookService(bookId);

        Map<String, Object> map = new HashMap<>();
        map.put("UserScoreResponse", newUserScoreResponse);
        map.put("BookResponse", bookResponse);
        return map;
    }

    @Override
    public UserScoreResponse getUserScore(long userScoreId) {
        return UserScoreConverter.convertUserScoreToUserScoreResponse(findUserScoreById(userScoreId));
    }

    @Override
    public UserScoreResponse getUserScoreByUserId(long bookId, long userId) {
        // Check that book and user exist
        bookService.findBookById(bookId);
        userService.findUserById(userId);

        try {
            return UserScoreConverter.convertUserScoreToUserScoreResponse(
                    findUserScoreByBookIdAndUserId(bookId, userId));
        } catch (ResourceNotFoundException e) {
            return UserScoreResponse.builder()
                    .bookId(bookId)
                    .userId(userId)
                    .build();
        }
    }

    @Override
    public Map<String, Object> updateUserScore( final long userScoreId, final UserScoreRequest userScoreRequest) {
        final UserScore userScore = findUserScoreById(userScoreId);

        userScoreRequestValidator.validateScore(userScoreRequest.getScore());
        userScore.setScore(userScoreRequest.getScore());

        final UserScore updatedUserScore = userScoreRepository.saveAndFlush(userScore);
        final UserScoreResponse userScoreResponse = UserScoreConverter.convertUserScoreToUserScoreResponse(updatedUserScore);
        final BookResponse bookResponse = updateUserScoreAverageInBookService(userScore.getBook().getId());

        Map<String, Object> map = new HashMap<>();
        map.put("UserScoreResponse", userScoreResponse);
        map.put("BookResponse", bookResponse);
        return map;
    }

    @Override
    public BookResponse deleteUserScore(long userScoreId) {
        final UserScore userScore = findUserScoreById(userScoreId);
        final long bookId = userScore.getBook().getId();

        userScoreRepository.delete(userScore);
        userScoreRepository.flush();

       bookService.incrementUserScoreCount(bookId, -1);
       return updateUserScoreAverageInBookService(bookId);
    }

    public UserScore findUserScoreByBookIdAndUserId(long bookId, long userId) {
        return userScoreRepository.findByBookIdAndUserId(bookId, userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("UserScore not found");
        });
    }

    public UserScore findUserScoreById(final long userScoreId) {
        return userScoreRepository.findById(userScoreId).orElseThrow(() -> {
            throw new ResourceNotFoundException("UserScore not found");
        });
    }

    private BookResponse updateUserScoreAverageInBookService(final long bookId) {
        final Book book = bookService.findBookById(bookId);
        final Float scoreAverage;
        if (book.getUserScoreCount() == 0) {
            scoreAverage = null;
        } else {
            scoreAverage = userScoreRepository.calculateScoreAverage(bookId);
        }
        return bookService.updateUserScoreAverage(bookId, scoreAverage);
    }
}
