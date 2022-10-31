package pl.milej.michal.wordofreaders.book;

import org.springframework.data.domain.Page;

public interface BookService {
    BookResponse addBook(final BookRequest bookRequest);
    BookResponse getBook(final long id);
    Page<BookResponse> getBooks(final Integer pageNumber, final Integer pageSize);
    BookResponse updateBook(final long id, final BookRequest bookRequest);
    BookResponse assignAuthor(final long bookId, final long authorId);
    BookResponse removeAuthor(final long bookId, final long authorId);
    BookResponse assignCover(final long bookId, final long coverId);
    void deleteBook(final long id);
}
