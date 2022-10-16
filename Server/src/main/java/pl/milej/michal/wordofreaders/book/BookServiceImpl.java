package pl.milej.michal.wordofreaders.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Override
    public BookData addBook(BookData bookData) {
        if (!StringUtils.hasText(bookData.getTitle()) || bookData.getCover() == null || bookData.getReleaseDate() == null) {
            throw new RequiredVariablesNotSetException("Variable title, releaseDate or cover has not been set");
        }

        final Book savedBook = bookRepository.save(BookConverter.convertToBook(bookData));
        return BookConverter.convertToBookData(savedBook);
    }

    @Override
    public BookData getBook(long id) {
        final Optional<Book> book = bookRepository.findById(id);
        return BookConverter.convertToBookData(book.orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        }));
    }

    @Override
    public BookData updateBook(long id, BookData bookData) {
        final Book existingBook = bookRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        });

        if (StringUtils.hasText(bookData.getTitle())) {
            existingBook.setTitle(bookData.getTitle());
        }
        if (bookData.getReleaseDate() != null) {
            existingBook.setReleaseDate(bookData.getReleaseDate());
        }
        existingBook.setAuthors(bookData.getAuthors());
        if (bookData.getCover() != null) {
            existingBook.setCover(bookData.getCover());
        }

        return BookConverter.convertToBookData(bookRepository.save(existingBook));
    }
}
