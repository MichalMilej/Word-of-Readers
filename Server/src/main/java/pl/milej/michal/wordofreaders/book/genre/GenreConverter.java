package pl.milej.michal.wordofreaders.book.genre;

public class GenreConverter {

    public static GenreResponse convertToGenreResponse(final Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
