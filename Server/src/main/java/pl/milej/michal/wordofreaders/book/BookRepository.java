package pl.milej.michal.wordofreaders.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.milej.michal.wordofreaders.book.genre.Genre;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);
    Optional<Book> findByIsbnEquals(String isbn);
    Optional<Book> findByTitleContainsIgnoreCaseAndReleaseDateEquals(String title, Date releaseDate);

    Page<Book> findAllByGenresIn(Set<Genre> genres, Pageable pageable);
    Page<Book> findAllByTitleContainsIgnoreCaseAndGenresIn(String title, Set<Genre> genres, Pageable pageable);
}
