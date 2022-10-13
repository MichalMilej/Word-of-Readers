package pl.milej.michal.wordofreaders.book;


public class BookConverter {

    public static Book convertToBook(final BookData bookData) {
        final Book book = new Book();
        book.setTitle(book.getTitle());
        book.setAuthors(bookData.getAuthors());
        book.setReleaseDate(bookData.getReleaseDate());
        book.setCover(book.getCover());
        return book;
    }

    public static BookData convertToBookData(final Book book) {
        return new BookData.BookDataBuilder()
                .id(book.getId())
                .title(book.getTitle())
                .releaseDate(book.getReleaseDate())
                .authors(book.getAuthors())
                .cover(book.getCover())
                .build();
    }
}
