package pl.milej.michal.wordofreaders.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    final AuthorServiceImpl authorService;

    @PostMapping
    public ResponseEntity<AuthorData> addAuthor(@RequestBody final AuthorData authorData) {
        return authorService.addAuthor(authorData);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorData> getAuthor(@PathVariable final Long id) {
        return authorService.getAuthor(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorData> updateAuthor(@RequestBody final AuthorData authorData) {
        return authorService.updateAuthor(authorData);
    }
}
