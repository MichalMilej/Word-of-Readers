package pl.milej.michal.wordofreaders.book;


import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.author.AuthorConverter;
import pl.milej.michal.wordofreaders.publisher.Publisher;
import pl.milej.michal.wordofreaders.publisher.PublisherConverter;

import java.util.Optional;
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
                .authorIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()))
                .coverId(book.getCover().getId())
                .publisherIds(book.getPublishers().stream().map(Publisher::getId).collect(Collectors.toSet()))
                .userScoreAverage(book.getUserScoreAverage())
                .userScoreCount(book.getUserScoreCount())
                .build();
    }
}
