package pl.milej.michal.wordofreaders.publisher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.milej.michal.wordofreaders.book.Book;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "publisher_books",
            joinColumns = @JoinColumn(name = "publisher_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "book_id", nullable = false)
    )
    private Set<Book> books;
}
