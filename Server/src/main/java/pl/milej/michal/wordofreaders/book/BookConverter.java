package pl.milej.michal.wordofreaders.book;


import pl.milej.michal.wordofreaders.author.AuthorConverter;
import pl.milej.michal.wordofreaders.book.cover.CoverConverter;
import pl.milej.michal.wordofreaders.book.genre.GenreConverter;
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
                .coverResponse(book.getCover() != null ?
                        CoverConverter.convertToCoverResponse(book.getCover()) : null)
                .publisherResponse(book.getPublisher() != null ?
                        PublisherConverter.convertToPublisherResponse(book.getPublisher()) : null)
                .genreResponses(book.getGenres() != null ? book.getGenres().stream()
                        .map(GenreConverter::convertToGenreResponse).collect(Collectors.toSet()) : null)
                .userScoreAverage(book.getUserScoreAverage())
                .userScoreCount(book.getUserScoreCount())
                .build();
    }
}
