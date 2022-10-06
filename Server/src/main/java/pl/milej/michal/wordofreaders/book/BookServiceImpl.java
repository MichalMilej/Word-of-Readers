package pl.milej.michal.wordofreaders.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Override
    public ResponseEntity<BookResponse> addBook(Book book) {
        if (!StringUtils.hasText(book.getTitle()) || book.getCover() == null || book.getReleaseDate() == null) {
            throw new RequiredVariablesNotSetException("Variable title, releaseDate or cover has not been set");
        }

        final Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(new BookResponse.BookResponseBuilder()
                .id(savedBook.getId())
                .title(savedBook.getTitle())
                .releaseDate(savedBook.getReleaseDate())
                .authors(savedBook.getAuthors())
                .cover(savedBook.getCover())
                .build(),
                HttpStatus.CREATED);
    }
}
