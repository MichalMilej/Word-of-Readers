package pl.milej.michal.wordofreaders.author;

import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.book.Book;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
public class AuthorData {
    private long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthDate;
    private Date deathDate;
    private Set<Book> books;
}
