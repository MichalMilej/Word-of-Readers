package pl.milej.michal.wordofreaders.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.milej.michal.wordofreaders.book.Book;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birth_date")
    private Date birthDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "death_date")
    private Date deathDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
