package pl.milej.michal.wordofreaders.book;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class BookController {

    final BookServiceImpl bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookData addBook(@RequestBody final BookData bookData) {
        return bookService.addBook(bookData);
    }

    @GetMapping("/{id}")
    BookData getBook(@PathVariable final long id) {
        return bookService.getBook(id);
    }

    @PutMapping("/{id}")
    BookData updateBook(@PathVariable final long id, final BookData bookData) {
        return bookService.updateBook(id, bookData);
    }



}
