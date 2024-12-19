package pl.milej.michal.worldofreaders.book.score;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserScoreResponse {

    private Long id;
    private Integer score;
    private Long bookId;
    private Long userId;
}
