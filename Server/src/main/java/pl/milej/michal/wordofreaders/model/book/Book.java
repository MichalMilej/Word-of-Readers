package pl.milej.michal.wordofreaders.model.book;

import pl.milej.michal.wordofreaders.model.author.Author;
import pl.milej.michal.wordofreaders.model.cover.Cover;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @ManyToOne
    @JoinColumn(name = "cover_id", nullable = false)
    private Cover cover;
}
