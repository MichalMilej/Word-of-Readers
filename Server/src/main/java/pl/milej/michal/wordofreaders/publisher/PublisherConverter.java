package pl.milej.michal.wordofreaders.publisher;

import pl.milej.michal.wordofreaders.book.Book;

import java.util.stream.Collectors;

public class PublisherConverter {

    public static PublisherResponse convertToPublisherResponse(final Publisher publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .bookIds(publisher.getBooks().stream().map(Book::getId).collect(Collectors.toSet()))
                .build();
    }
}
