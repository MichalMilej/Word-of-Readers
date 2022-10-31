package pl.milej.michal.wordofreaders.book;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class BookRequest {

    private String title;
    private Date releaseDate;
    private String description;
    private Long coverId;
}