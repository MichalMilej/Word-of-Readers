package pl.milej.michal.wordofreaders.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookService {
    ResponseEntity<BookData> addBook(@RequestBody final BookData bookData);
}
