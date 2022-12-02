package pl.milej.michal.wordofreaders.book;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class BookController {

    final BookServiceImpl bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse addBook(@RequestBody final BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @GetMapping("/{bookId}")
    BookResponse getBook(@PathVariable final long bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping
    Page<BookResponse> getBooks(@RequestParam(required = false) final String title,
                                @RequestParam(required = false) final List<Long> genresIds,
                                @RequestParam final Integer pageNumber,
                                @RequestParam final Integer pageSize) {
        return bookService.getBooks(title, genresIds, pageNumber, pageSize);
    }

    @PatchMapping("/{bookId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse updateBook(@PathVariable final long bookId, @RequestBody final BookRequest bookRequest) {
        return bookService.updateBook(bookId, bookRequest);
    }

    @PatchMapping("/{bookId}/authors/{authorId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse assignAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        return bookService.assignAuthor(bookId, authorId);
    }

    @PatchMapping("/{bookId}/genres/{genreId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse assignGenre(@PathVariable final long bookId, @PathVariable final long genreId) {
        return bookService.assignGenre(bookId, genreId);
    }

    @PatchMapping("/{bookId}/publisher/{publisherId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse updatePublisher(@PathVariable final long bookId, @PathVariable final long publisherId) {
        return bookService.updatePublisher(bookId, publisherId);
    }

    @PatchMapping("/{bookId}/cover/{coverId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse updateCover(@PathVariable final long bookId, @PathVariable final long coverId) {
        return bookService.updateCover(bookId, coverId);
    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse removeAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        return bookService.removeAuthor(bookId, authorId);
    }

    @DeleteMapping("/{bookId}/genres/{genreId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    BookResponse removeGenre(@PathVariable final long bookId, @PathVariable final long genreId) {
        return bookService.removeGenre(bookId, genreId);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    void deleteBook(@PathVariable final long bookId) {
        bookService.deleteBook(bookId);
    }

}
