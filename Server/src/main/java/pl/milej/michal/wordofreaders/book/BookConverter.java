package pl.milej.michal.wordofreaders.book;


public class BookConverter {

    public static Book convertBookRequestToBook(final BookRequest bookRequest) {
        final Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setReleaseDate(bookRequest.getReleaseDate());
        return book;
    }

    public static BookResponse convertToBookResponse(final Book book) {
        return new BookResponse.BookResponseBuilder()
                .id(book.getId())
                .title(book.getTitle())
                .releaseDate(book.getReleaseDate())
                .description(book.getDescription())
                .authors(book.getAuthors())
                .cover(book.getCover())
                .build();
    }
}
