package pl.milej.michal.wordofreaders.book;

public interface BookService {
    BookData addBook(final BookData bookData);
    BookData getBook(final long id);
    BookData updateBook(final long id, final BookData bookData);
}
