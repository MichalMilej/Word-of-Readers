package pl.milej.michal.wordofreaders.model.author;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.milej.michal.wordofreaders.model.book.Book;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Setter
@Getter
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

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "death_date")
    private Date deathDate;

    @ManyToMany()
    @JoinTable(
            name = "book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Book> books;

    public Author(final String firstName,
                  final String lastName,
                  final Date birthDate,
                  final Date deathDate,
                  final Set<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.books = books;
    }

    public Author() {}
}
