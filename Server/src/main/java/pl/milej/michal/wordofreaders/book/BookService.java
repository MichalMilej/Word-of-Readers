package pl.milej.michal.wordofreaders.book;

import org.springframework.data.domain.Page;
import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.book.cover.Cover;

public interface BookService {
    BookResponse addBook(final BookRequest bookRequest);

    BookResponse getBook(final long id);
    Page<BookResponse> getBooks(final Integer pageNumber, final Integer pageSize);

    BookResponse updateBook(final long id, final BookRequest bookRequest);
    BookResponse assignAuthor(final long bookId, final long authorId);
    BookResponse assignCover(final long bookId, final long coverId);
    BookResponse assignPublisher(final long bookId, final long publisherId);
    BookResponse updateUserScoreAverage(final long bookId, final float userScoreAverage);
    BookResponse updateUserScoreCount(final long bookId, final int userScoreCount);

    BookResponse removeAuthor(final long bookId, final long authorId);

    Book findBookById(final long bookId);
    Cover findCoverById(final long coverId);
    Author findAuthorById(final long authorId);
    void deleteBook(final long id);
}
