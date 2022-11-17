package pl.milej.michal.wordofreaders.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainsIgnoreCase(String title, Pageable pageable);
    Optional<Book> findByTitleContainsIgnoreCaseAndReleaseDateEquals(String title, Date releaseDate);
}
