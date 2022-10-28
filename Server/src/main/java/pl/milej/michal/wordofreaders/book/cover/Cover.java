package pl.milej.michal.wordofreaders.book.cover;

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
    private Long id;

    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "cover")
    private Set<Book> books;

    public Cover(final String name, final String location) {
        this.name = name;
        this.location = location;
    }
}
