package pl.milej.michal.wordofreaders.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@NoArgsConstructor
public class BookRequest {

    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String description;
    private Long publisherId;
    private Long coverId;
}