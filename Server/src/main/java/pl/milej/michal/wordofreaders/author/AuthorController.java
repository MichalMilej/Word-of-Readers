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
    public AuthorData addAuthor(@RequestBody final AuthorData authorData) {
        return authorService.addAuthor(authorData);
    }

    @GetMapping("/{id}")
    public AuthorData getAuthor(@PathVariable final long id) {
        return authorService.getAuthor(id);
    }

    @PutMapping("/{id}")
    public AuthorData updateAuthor(@PathVariable final long id, @RequestBody final AuthorData authorData) {
        return authorService.updateAuthor(id, authorData);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable final long id) {
        authorService.deleteAuthor(id);
    }
}
