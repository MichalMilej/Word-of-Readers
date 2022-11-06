package pl.milej.michal.wordofreaders.book.score;

public class UserScoreConverter {

    public static UserScoreResponse convertUserScoreToUserScoreResponse(final UserScore userScore) {
        return UserScoreResponse.builder()
                .id(userScore.getId())
                .score(userScore.getScore())
                .bookId(userScore.getBook().getId())
                .userId(userScore.getUser().getId())
                .build();
    }
}
