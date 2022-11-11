package pl.milej.michal.wordofreaders.book;


import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.publisher.Publisher;

import java.util.stream.Collectors;

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
                .authorIds(book.getAuthors() != null ?
                        book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()) : null)
                .coverId(book.getCover() != null ?
                        book.getCover().getId() : null)
                .publisherIds(book.getPublishers() != null ?
                        book.getPublishers().stream().map(Publisher::getId).collect(Collectors.toSet()) : null)
                .userScoreAverage(book.getUserScoreAverage())
                .userScoreCount(book.getUserScoreCount())
                .build();
    }
}
