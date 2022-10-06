package pl.milej.michal.wordofreaders.author;

import org.springframework.http.ResponseEntity;

public interface AuthorService {

    ResponseEntity<AuthorData> addAuthor(final AuthorData authorData);
    ResponseEntity<AuthorData> getAuthor(final Long id);
    ResponseEntity<AuthorData> updateAuthor(final AuthorData authorData);
}
