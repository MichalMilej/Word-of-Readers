package pl.milej.michal.worldofreaders.book.genre;

import java.util.List;

public interface GenreService {

    GenreResponse addGenre(final GenreRequest genreRequest);
    List<GenreResponse> getGenres();
    Genre findGenreById(final long genreId);
}
