package pl.milej.michal.wordofreaders.book;


import pl.milej.michal.wordofreaders.author.AuthorConverter;

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
                .authorResponses(Optional.ofNullable(book.getAuthors()).isEmpty() ? null :
                        book.getAuthors()
                        .stream()
                        .map(AuthorConverter::convertAuthorToAuthorResponse)
                        .collect(Collectors.toSet()))
                .cover(book.getCover())
                .userScoreAverage(book.getUserScoreAverage())
                .userScoreCount(book.getUserScoreCount())
                .build();
    }
}
