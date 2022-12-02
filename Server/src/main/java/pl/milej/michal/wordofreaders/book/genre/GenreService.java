package pl.milej.michal.wordofreaders.book.genre;

import java.util.List;

public interface GenreService {

    GenreResponse addGenre(final GenreRequest genreRequest);
    List<GenreResponse> getGenres();
    Genre findGenreById(final long genreId);
}
