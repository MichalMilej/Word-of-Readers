package pl.milej.michal.wordofreaders.book.cover;

import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.book.Book;

import java.util.Set;

@Data
@Builder
public class CoverData {
    private Long id;
    private String location;
    private String coverName;
    private Set<Book> books;
}
