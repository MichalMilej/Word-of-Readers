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
    ResponseEntity<BookData> addBook(@RequestBody final BookData bookData) {
        return bookService.addBook(bookData);
    }

}
