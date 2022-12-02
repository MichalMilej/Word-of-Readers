package pl.milej.michal.wordofreaders.book.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/genres")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class GenreController {
    final GenreServiceImpl genreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    GenreResponse addGenre(@RequestBody final GenreRequest genreRequest) {
        return genreService.addGenre(genreRequest);
    }

    @GetMapping
    List<GenreResponse> getGenres() {
        return genreService.getGenres();
    }
}
