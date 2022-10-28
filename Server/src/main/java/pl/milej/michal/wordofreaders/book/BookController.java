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
    BookResponse addBook(@RequestBody final BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @GetMapping("/{bookId}")
    BookResponse getBook(@PathVariable final long bookId) {
        return bookService.getBook(bookId);
    }

    @PutMapping("/{bookId}")
    BookResponse updateBook(@PathVariable final long bookId, @RequestBody final BookRequest bookRequest) {
        return bookService.updateBook(bookId, bookRequest);
    }

    @PutMapping("/{bookId}/authors/{authorId}")
    BookResponse assignAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        return bookService.assignAuthor(bookId, authorId);
    }

    @PutMapping("/{bookId}/{coverId}")
    BookResponse assignCover(@PathVariable final long bookId, @PathVariable final long coverId) {
        return bookService.assignCover(bookId, coverId);
    }

    @DeleteMapping("/{bookId}")
    void deleteBook(@PathVariable final long bookId) {
        bookService.deleteBook(bookId);
    }



}
