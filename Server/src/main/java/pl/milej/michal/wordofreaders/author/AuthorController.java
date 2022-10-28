package pl.milej.michal.wordofreaders.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class AuthorController {

    final AuthorServiceImpl authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse addAuthor(@RequestBody final AuthorRequest authorRequest) {
        return authorService.addAuthor(authorRequest);
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable final long id) {
        return authorService.getAuthor(id);
    }

    @PutMapping("/{id}")
    public AuthorResponse updateAuthor(@PathVariable final long id, @RequestBody final AuthorRequest authorRequest) {
        return authorService.updateAuthor(id, authorRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable final long id) {
        authorService.deleteAuthor(id);
    }
}
