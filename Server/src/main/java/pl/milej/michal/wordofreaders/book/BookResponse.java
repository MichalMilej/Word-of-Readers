package pl.milej.michal.wordofreaders.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.author.AuthorResponse;
import pl.milej.michal.wordofreaders.book.cover.CoverResponse;
import pl.milej.michal.wordofreaders.book.genre.GenreResponse;
import pl.milej.michal.wordofreaders.publisher.PublisherResponse;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
public class BookResponse {

    private long id;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;
    private String isbn;
    private String description;
    private Set<AuthorResponse> authorResponses;
    private CoverResponse coverResponse;
    private PublisherResponse publisherResponse;
    private Set<GenreResponse> genreResponses;
    private Float userScoreAverage;
    private Integer userScoreCount;
}
