package pl.milej.michal.wordofreaders.book;

public interface BookService {
    BookResponse addBook(final BookRequest bookRequest);
    BookResponse getBook(final long id);
    BookResponse updateBook(final long id, final BookRequest bookRequest);
    BookResponse assignAuthor(final long bookId, final long authorId);
    BookResponse assignCover(final long bookId, final long coverId);
    void deleteBook(final long id);
}
