package pl.milej.michal.worldofreaders.book.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.milej.michal.worldofreaders.exception.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GenreServiceImpl implements GenreService {
    final GenreRepository genreRepository;

    @Override
    public GenreResponse addGenre(final GenreRequest genreRequest) {
        if (!StringUtils.hasText(genreRequest.getName())) {
            throw new BadRequestException("Genre name is empty");
        }
        if (genreRepository.findGenreByNameIgnoreCase(genreRequest.getName()).isPresent()) {
            throw new BadRequestException("This genre has already been added to database");
        }
        final Genre genre = new Genre();
        genre.setName(genreRequest.getName());
        final Genre savedGenre = genreRepository.save(genre);

        return GenreConverter.convertToGenreResponse(savedGenre);
    }

    @Override
    public List<GenreResponse> getGenres() {
        return genreRepository.findAll()
                .stream()
                .map(GenreConverter::convertToGenreResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Genre findGenreById(long genreId) {
        return genreRepository.findById(genreId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Genre not found");
        });
    }
}
