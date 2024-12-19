package pl.milej.michal.worldofreaders.publisher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.milej.michal.worldofreaders.book.Book;

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

    @OneToMany(mappedBy = "publisher")
    @JsonIgnore
    private Set<Book> books;
}
