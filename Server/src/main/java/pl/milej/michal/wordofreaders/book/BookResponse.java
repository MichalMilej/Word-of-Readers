package pl.milej.michal.wordofreaders.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.author.AuthorResponse;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
public class BookResponse {

    private long id;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;
    private String description;
    private Set<AuthorResponse> authorResponses;
    private Long coverId;
    private Set<Long> publisherIds;
    private Float userScoreAverage;
    private Integer userScoreCount;
}
