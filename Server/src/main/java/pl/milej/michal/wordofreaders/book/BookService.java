package pl.milej.michal.wordofreaders.book;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    BookResponse addBook(final BookRequest bookRequest);

    BookResponse getBook(final long id);
    Page<BookResponse> getBooks(final String title, final List<Long> genresIds,
                                final Integer pageNumber, final Integer pageSize);
    BookResponse assignAuthor(final long bookId, final long authorId);
    BookResponse assignGenre(final long bookId, final long genreId);

    BookResponse updateBook(final long id, final BookRequest bookRequest);
    BookResponse updateCover(final long bookId, final long coverId);
    BookResponse updatePublisher(final long bookId, final long publisherId);
    BookResponse updateUserScoreAverage(final long bookId, final float userScoreAverage);
    BookResponse updateUserScoreCount(final long bookId, final int userScoreCount);

    BookResponse removeAuthor(final long bookId, final long authorId);
    BookResponse removeGenre(final long bookId, final long genreId);

    Book findBookById(final long bookId);
    void deleteBook(final long id);
}
