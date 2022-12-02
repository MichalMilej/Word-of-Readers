package pl.milej.michal.wordofreaders.book.genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findGenreByNameIgnoreCase(String name);
}
