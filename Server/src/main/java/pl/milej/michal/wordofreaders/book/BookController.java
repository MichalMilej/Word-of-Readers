package pl.milej.michal.wordofreaders.book;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping
    Page<BookResponse> getBooks(@RequestParam final Integer pageNumber, @RequestParam final Integer pageSize) {
        return bookService.getBooks(pageNumber, pageSize);
    }

    @PatchMapping("/{bookId}")
    BookResponse updateBook(@PathVariable final long bookId, @RequestBody final BookRequest bookRequest) {
        return bookService.updateBook(bookId, bookRequest);
    }

    @PatchMapping("/{bookId}/authors/{authorId}")
    BookResponse assignAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        return bookService.assignAuthor(bookId, authorId);
    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    BookResponse removeAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        return bookService.removeAuthor(bookId, authorId);
    }

    @PatchMapping("/{bookId}/cover/{coverId}")
    BookResponse assignCover(@PathVariable final long bookId, @PathVariable final long coverId) {
        return bookService.assignCover(bookId, coverId);
    }

    @DeleteMapping("/{bookId}")
    void deleteBook(@PathVariable final long bookId) {
        bookService.deleteBook(bookId);
    }

}
