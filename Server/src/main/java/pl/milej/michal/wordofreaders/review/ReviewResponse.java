package pl.milej.michal.wordofreaders.review;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class ReviewResponse {

    private Long id;
    private String text;
    private Date publicationDate;
    private Integer likes;
    private Integer dislikes;
    private Long bookId;
    private Long userId;
}