package pl.milej.michal.wordofreaders.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.book.cover.Cover;
import pl.milej.michal.wordofreaders.book.score.UserScore;
import pl.milej.michal.wordofreaders.publisher.Publisher;
import pl.milej.michal.wordofreaders.review.Review;

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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "release_date")
    private Date releaseDate;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false)
    )
    private Set<Author> authors;

    @ManyToMany(mappedBy = "books")
    private Set<Publisher> publishers;

    @ManyToOne
    @JoinColumn(name = "cover_id")
    private Cover cover;

    @Column(name = "user_score_average")
    private Float userScoreAverage;

    @Column(name = "user_score_count")
    private Integer userScoreCount = 0;

    @OneToMany(mappedBy = "book")
    private Set<UserScore> userScores;

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;
}
