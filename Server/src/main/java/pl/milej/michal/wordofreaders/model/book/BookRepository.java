package pl.milej.michal.wordofreaders.model.book;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book findBookById(long id);
    List<Book> findBookByTitle(String title);
}
