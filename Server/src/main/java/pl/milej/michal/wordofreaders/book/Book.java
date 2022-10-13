package pl.milej.michal.wordofreaders.book;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.cover.Cover;

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

    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @ManyToOne
    @JoinColumn(name = "cover_id", nullable = false)
    private Cover cover;
}
