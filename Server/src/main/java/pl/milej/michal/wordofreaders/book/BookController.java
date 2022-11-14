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
    Page<BookResponse> getBooks(@RequestParam(required = false) final String title,
                                @RequestParam final Integer pageNumber,
                                @RequestParam final Integer pageSize) {
        if (title == null) {
            return bookService.getBooks(pageNumber, pageSize);
        } else {
            return bookService.getBooksByTitle(title, pageNumber, pageSize);
        }
    }

    @PatchMapping("/{bookId}")
    BookResponse updateBook(@PathVariable final long bookId, @RequestBody final BookRequest bookRequest) {
        return bookService.updateBook(bookId, bookRequest);
    }

    @PatchMapping("/{bookId}/authors/{authorId}")
    BookResponse assignAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        return bookService.assignAuthor(bookId, authorId);
    }

    @PatchMapping("/{bookId}/publishers/{publisherId}")
    BookResponse assignPublisher(@PathVariable final long bookId, @PathVariable final long publisherId) {
        return bookService.assignPublisher(bookId, publisherId);
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
