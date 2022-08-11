package pl.milej.michal.wordofreaders.model.cover;

import pl.milej.michal.wordofreaders.model.book.Book;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cover {

    @Id
    @GeneratedValue
    Long id;

    @Lob
    byte[] content;

    @OneToMany(mappedBy = "cover")
    Set<Book> books;
}
