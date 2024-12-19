package pl.milej.michal.worldofreaders.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.milej.michal.worldofreaders.author.Author;
import pl.milej.michal.worldofreaders.author.AuthorServiceImpl;
import pl.milej.michal.worldofreaders.book.cover.Cover;
import pl.milej.michal.worldofreaders.book.cover.CoverServiceImpl;
import pl.milej.michal.worldofreaders.book.genre.Genre;
import pl.milej.michal.worldofreaders.book.genre.GenreServiceImpl;
import pl.milej.michal.worldofreaders.exception.*;
import pl.milej.michal.worldofreaders.publisher.Publisher;
import pl.milej.michal.worldofreaders.publisher.PublisherServiceImpl;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final private BookRepository bookRepository;
    final private AuthorServiceImpl authorService;
    final private PublisherServiceImpl publisherService;
    final private CoverServiceImpl coverService;
    final private GenreServiceImpl genreService;

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        final String title = bookRequest.getTitle();
        final Date releaseDate = bookRequest.getReleaseDate();

        if (!StringUtils.hasText(title) || bookRequest.getReleaseDate() == null) {
            throw new RequiredVariablesNotSetException("Variable title or releaseDate has not been set");
        }
        if (bookRepository.findByTitleContainsIgnoreCaseAndReleaseDateEquals(title, releaseDate).isPresent()) {
            throw new LimitExceededException("There is a book with this name and release date in database");
        }

        final Book book = new Book();
        book.setTitle(title);
        book.setDescription(bookRequest.getDescription());
        book.setReleaseDate(releaseDate);
        setBookIsbn(book, bookRequest.getIsbn());
        if (bookRequest.getPublisherId() != null) {
            final Publisher publisher = publisherService.findPublisherById(bookRequest.getPublisherId());
            book.setPublisher(publisher);
        }
        final Cover cover;
        if (bookRequest.getCoverId() != null) {
            cover = coverService.findCoverById(bookRequest.getCoverId());
        } else {
            cover = coverService.findCoverById(1);
        }
        book.setCover(cover);
        final Book savedBook = bookRepository.save(book);

        return BookConverter.convertToBookResponse(savedBook);
    }

    @Override
    public BookResponse getBook(long bookId) {
        return BookConverter.convertToBookResponse(findBookById(bookId));
    }

    @Override
    public Page<BookResponse> getBooks(final String title,
                                       final String authorLastName,
                                       final List<Long> genresIds,
                                       final Integer pageNumber,
                                       final Integer pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));

        final Page<Book> books;
        if (title == null && authorLastName == null) {
            if (genresIds == null) {
                books = bookRepository.findAll(pageable);
            } else  {
                books = bookRepository.findAllByGenresIdsIn(genresIds, pageable);
            }
        } else if (title != null) {
            if (genresIds == null) {
                books = bookRepository.findAllByTitleContainsIgnoreCase(title, pageable);
            } else  {
                books = bookRepository.findAllByTitleIgnoreCaseAndGenresIdsIn(title, genresIds, pageable);
            }
        } else {
            if (genresIds == null) {
                books = bookRepository.findAllByAuthorLastNameIgnoreCase(authorLastName, pageable);
            } else {
                books = bookRepository.findAllByAuthorLastNameIgnoreCaseAndGenresIdsIn(authorLastName, genresIds, pageable);
            }
        }
        return books.map(BookConverter::convertToBookResponse);
    }

    @Override
    public BookResponse updateBook(long id, BookRequest bookRequest) {
        final Book existingBook = findBookById(id);

        if (StringUtils.hasText(bookRequest.getTitle())) {
            existingBook.setTitle(bookRequest.getTitle());
        }
        if (bookRequest.getReleaseDate() != null) {
            existingBook.setReleaseDate(bookRequest.getReleaseDate());
        }
        setBookIsbn(existingBook, bookRequest.getIsbn());
        if (bookRequest.getDescription() != null) {
            existingBook.setDescription(bookRequest.getDescription());
        }
        if (bookRequest.getPublisherId() != null) {
            final Publisher publisher = publisherService.findPublisherById(bookRequest.getPublisherId());
            existingBook.setPublisher(publisher);
        }
        if (bookRequest.getCoverId() !=  null) {
            final Cover cover = coverService.findCoverById(bookRequest.getCoverId());
            existingBook.setCover(cover);
        }

        return BookConverter.convertToBookResponse(bookRepository.save(existingBook));
    }

    @Override
    public BookResponse assignAuthor(long bookId, long authorId) {
        final Book book = findBookById(bookId);
        final Author author = authorService.findAuthorById(authorId);

        if (book.getAuthors().contains(author)) {
            throw new RelationAlreadySetException("This author has already been assigned to this book");
        }

        book.getAuthors().add(author);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse assignGenre(long bookId, long genreId) {
        final Book book = findBookById(bookId);
        final Genre genre = genreService.findGenreById(genreId);

        if (book.getGenres().contains(genre)) {
            throw new RelationAlreadySetException("This genre has already been assigned to this book");
        }

        book.getGenres().add(genre);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updatePublisher(long bookId, long publisherId) {
        final Book book = findBookById(bookId);
        final Publisher publisher = publisherService.findPublisherById(publisherId);

        book.setPublisher(publisher);

        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updateCover(final long bookId, final long coverId) {
        final Book book = findBookById(bookId);
        final Cover cover = coverService.findCoverById(coverId);

        book.setCover(cover);

        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updateUserScoreAverage(final long bookId, Float scoreAverage) {
        final Book book = findBookById(bookId);
        if (book.getUserScoreCount() == 0) {
            book.setUserScoreAverage(null);
        } else {
            if (scoreAverage < 1 || scoreAverage > 10) {
                throw new BadServerRequestException("Incorrect score average value");
            }
            book.setUserScoreAverage(scoreAverage);
        }
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse incrementUserScoreCount(final long bookId, final int incrementation) {
        final Book book = findBookById(bookId);
        final int currentCount = book.getUserScoreCount();
        if (currentCount + incrementation < 0) {
            throw new BadRequestException("Wrong incrementation value");
        }

        book.setUserScoreCount(currentCount + incrementation);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse removeAuthor(long bookId, long authorId) {
        final Book book = findBookById(bookId);
        final Author author = authorService.findAuthorById(authorId);

        book.getAuthors().remove(author);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse removeGenre(long bookId, long genreId) {
        final Book book = findBookById(bookId);
        final Genre genre = genreService.findGenreById(genreId);

        book.getGenres().remove(genre);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public Book findBookById(final long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book not found");
        });
    }

    private void setBookIsbn(final Book book, String isbn) {
        if (isbn != null) {
            if (bookRepository.findByIsbnEquals(isbn).isPresent()) {
                throw new BadRequestException("This ISBN number has already been assigned");
            } else {
                book.setIsbn(isbn);
            }
        }
    }
}
