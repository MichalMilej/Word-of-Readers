package pl.milej.michal.wordofreaders.book;


import pl.milej.michal.wordofreaders.author.AuthorConverter;
import pl.milej.michal.wordofreaders.publisher.Publisher;
import pl.milej.michal.wordofreaders.publisher.PublisherConverter;

import java.util.stream.Collectors;

public class BookConverter {

    public static BookResponse convertToBookResponse(final Book book) {
        return new BookResponse.BookResponseBuilder()
                .id(book.getId())
                .title(book.getTitle())
                .releaseDate(book.getReleaseDate())
                .description(book.getDescription())
                .authorResponses(book.getAuthors() != null ? book.getAuthors().stream()
                        .map(AuthorConverter::convertAuthorToAuthorResponse).collect(Collectors.toSet()) : null)
                .coverId(book.getCover() != null ?
                        book.getCover().getId() : null)
                .publisherResponse(book.getPublisher() != null ?
                        PublisherConverter.convertToPublisherResponse(book.getPublisher()) : null)
                .userScoreAverage(book.getUserScoreAverage())
                .userScoreCount(book.getUserScoreCount())
                .build();
    }
}
