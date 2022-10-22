package pl.milej.michal.wordofreaders.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.book.cover.Cover;
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

    @Column(name = "release_date")
    private Date releaseDate;

    @Column
    private String description;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @ManyToMany(mappedBy = "books")
    private Set<Publisher> publishers;

    @ManyToOne
    @JoinColumn(name = "cover_id", nullable = false)
    private Cover cover;

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;
}
