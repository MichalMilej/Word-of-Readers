package pl.milej.michal.wordofreaders.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.author.AuthorRepository;
import pl.milej.michal.wordofreaders.book.cover.Cover;
import pl.milej.michal.wordofreaders.book.cover.CoverRepository;
import pl.milej.michal.wordofreaders.exception.BadRequestException;
import pl.milej.michal.wordofreaders.exception.BadServerRequestException;
import pl.milej.michal.wordofreaders.exception.RelationAlreadySetException;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;
import pl.milej.michal.wordofreaders.publisher.Publisher;
import pl.milej.michal.wordofreaders.publisher.PublisherServiceImpl;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    final private BookRepository bookRepository;
    final private CoverRepository coverRepository;
    final private AuthorRepository authorRepository;
    final private PublisherServiceImpl publisherService;

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        if (!StringUtils.hasText(bookRequest.getTitle()) || bookRequest.getReleaseDate() == null) {
            throw new RequiredVariablesNotSetException("Variable title or releaseDate has not been set");
        }
        final Book savedBook = bookRepository.save(BookConverter.convertBookRequestToBook(bookRequest));

        return BookConverter.convertToBookResponse(savedBook);
    }

    @Override
    public BookResponse getBook(long bookId) {
        return BookConverter.convertToBookResponse(findBookById(bookId));
    }

    @Override
    public Page<BookResponse> getBooks(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return bookRepository.findAll(pageable).map(BookConverter::convertToBookResponse);
    }

    @Override
    public Page<BookResponse> getBooksByTitle(String title, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
        return bookRepository.findByTitleContains(title, pageable).map(BookConverter::convertToBookResponse);
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
        if (bookRequest.getDescription() != null) {
            existingBook.setDescription(bookRequest.getDescription());
        }

        return BookConverter.convertToBookResponse(bookRepository.save(existingBook));
    }

    @Override
    public BookResponse assignAuthor(long bookId, long authorId) {
        final Book book = findBookById(bookId);
        final Author author = findAuthorById(authorId);

        if (book.getAuthors().contains(author)) {
            throw new RelationAlreadySetException("This author has already been assigned to this book");
        }

        book.getAuthors().add(author);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse assignCover(final long bookId, final long coverId) {
        final Book book = findBookById(bookId);
        final Cover cover = findCoverById(coverId);

        book.setCover(cover);

        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse assignPublisher(long bookId, long publisherId) {
        final Book book = findBookById(bookId);
        final Publisher publisher = publisherService.findPublisherById(publisherId);

        if (book.getPublishers().contains(publisher)) {
            throw new RelationAlreadySetException("This publisher has already been assigned to this book");
        }

        book.getPublishers().add(publisher);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updateUserScoreAverage(final long bookId, final float userScoreAverage) {
        if (userScoreAverage < 0 || userScoreAverage > 10) {
            throw new BadServerRequestException("Wrong userScoreAverage");
        }
        final Book book = findBookById(bookId);
        book.setUserScoreAverage(userScoreAverage);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updateUserScoreCount(final long bookId, final int userScoreCount) {
        if (userScoreCount < 0) {
            throw new BadRequestException("Wrong userScoreTotalNumber");
        }
        final Book book = findBookById(bookId);
        book.setUserScoreCount(userScoreCount);
        return BookConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse removeAuthor(long bookId, long authorId) {
        final Book book = findBookById(bookId);
        final Author author = findAuthorById(authorId);

        book.getAuthors().remove(author);
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

    public Cover findCoverById(final long coverId) {
        return coverRepository.findById(coverId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Cover not found");
        });
    }

    public Author findAuthorById(final long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        });
    }
}
