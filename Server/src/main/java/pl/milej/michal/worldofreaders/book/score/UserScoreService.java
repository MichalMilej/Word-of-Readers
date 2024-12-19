package pl.milej.michal.worldofreaders.book.score;

import pl.milej.michal.worldofreaders.book.BookResponse;

import java.util.Map;

public interface UserScoreService {

   Map<String, Object> addUserScore(final long bookId, final UserScoreRequest userScoreRequest);
   UserScoreResponse getUserScore(final long userScoreId);
   UserScoreResponse getUserScoreByUserId(final long bookId, final long userId);
   Map<String, Object> updateUserScore(final long userScoreId, final UserScoreRequest userScoreRequest);
   BookResponse deleteUserScore(final long userScoreId);
}
