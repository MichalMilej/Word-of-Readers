package pl.milej.michal.wordofreaders.book;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    BookServiceImpl bookService;

    @PostMapping
    ResponseEntity<BookResponse> addBook(@RequestBody final Book book) {
        return bookService.addBook(book);
    }

}
