package pl.milej.michal.wordofreaders.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Override
    public ResponseEntity<BookData> addBook(BookData bookData) {
        if (!StringUtils.hasText(bookData.getTitle()) || bookData.getCover() == null || bookData.getReleaseDate() == null) {
            throw new RequiredVariablesNotSetException("Variable title, releaseDate or cover has not been set");
        }

        final Book savedBook = bookRepository.save(BookConverter.convertToBook(bookData));
        return ResponseEntity.ok(BookConverter.convertToBookData(savedBook));
    }


}
