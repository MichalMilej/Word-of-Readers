package pl.milej.michal.worldofreaders.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.milej.michal.worldofreaders.author.Author;
import pl.milej.michal.worldofreaders.book.cover.Cover;
import pl.milej.michal.worldofreaders.book.genre.Genre;
import pl.milej.michal.worldofreaders.book.score.UserScore;
import pl.milej.michal.worldofreaders.publisher.Publisher;
import pl.milej.michal.worldofreaders.book.review.Review;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private Date releaseDate;

    @Column(unique = true, length = 13)
    private String isbn;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false)
    )
    private Set<Author> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "cover_id", nullable = false)
    private Cover cover;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = false)
    )
    private Set<Genre> genres;

    @Column(name = "user_score_average")
    private Float userScoreAverage;

    @Column(name = "user_score_count")
    private Integer userScoreCount = 0;

    @OneToMany(mappedBy = "book")
    private Set<UserScore> userScores;

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;
}
