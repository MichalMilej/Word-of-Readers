package pl.milej.michal.worldofreaders.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);
    @Query("SELECT book FROM Book book JOIN book.authors a WHERE LOWER(a.lastName)"
            + "LIKE LOWER(CONCAT('%', CONCAT(:lastName, '%')))")
    Page<Book> findAllByAuthorLastNameIgnoreCase(String lastName, Pageable pageable);
    Optional<Book> findByIsbnEquals(String isbn);
    Optional<Book> findByTitleContainsIgnoreCaseAndReleaseDateEquals(String title, Date releaseDate);

    @Query("SELECT DISTINCT b FROM Book b JOIN b.genres g ON g.id in :genresIds")
    Page<Book> findAllByGenresIdsIn(List<Long> genresIds, Pageable pageable);
    @Query("SELECT DISTINCT b FROM Book b JOIN b.genres g ON LOWER(b.title)"
            + "LIKE LOWER(CONCAT('%', CONCAT(:title, '%'))) AND g.id in :genresIds")
    Page<Book> findAllByTitleIgnoreCaseAndGenresIdsIn(String title, List<Long> genresIds, Pageable pageable);
    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a ON LOWER(a.lastName)"
            + "LIKE LOWER(CONCAT('%', CONCAT(:authorLastName, '%')))"
            + "JOIN b.genres g ON g.id in :genresIds")
    Page<Book> findAllByAuthorLastNameIgnoreCaseAndGenresIdsIn(String authorLastName, List<Long> genresIds, Pageable pageable);
}
