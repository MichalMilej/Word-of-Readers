package pl.milej.michal.wordofreaders.cover;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.wordofreaders.book.Book;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cover {

    @Id
    @GeneratedValue
    Long id;

    @Lob
    byte[] content;

    @OneToMany(mappedBy = "cover")
    Set<Book> books;
}
