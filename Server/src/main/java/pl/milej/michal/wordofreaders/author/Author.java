package pl.milej.michal.wordofreaders.author;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.book.Book;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
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
            name = "author_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books;

    public Author() {}

    @Getter
    public static class AuthorBuilder {
        private String firstName;
        private String secondName;
        private String lastName;
        private Date birthDate;
        private Date deathDate;
        private Set<Book> books;

        public AuthorBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AuthorBuilder secondName(final String secondName) {
            this.secondName = secondName;
            return this;
        }

        public AuthorBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AuthorBuilder birthDate(final Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public AuthorBuilder deathDate(final Date deathDate) {
            this.deathDate = deathDate;
            return this;
        }

        public AuthorBuilder books(final Set<Book> books) {
            this.books = books;
            return this;
        }

        public Author build() {
            if (!StringUtils.hasText(getFirstName()) || !StringUtils.hasText(getLastName())) {
                throw new RequiredVariablesNotSetException("Variable firstName or lastName has not been set");
            }

            final Author author = new Author();
            author.firstName = firstName;
            author.secondName = secondName;
            author.lastName = lastName;
            author.birthDate = birthDate;
            author.deathDate = deathDate;
            author.books = books;
            return author;
        }

    }
}
