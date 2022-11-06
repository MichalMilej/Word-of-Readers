package pl.milej.michal.wordofreaders.book.score;

import lombok.Data;

@Data
public class UserScoreRequest {

    private Integer score;
    private Long bookId;
    private Long userId;
}
