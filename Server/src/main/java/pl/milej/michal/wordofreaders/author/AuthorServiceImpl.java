package pl.milej.michal.wordofreaders.author;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository authorRepository;

    @Override
    public ResponseEntity<AuthorData> addAuthor(final AuthorData authorData) {
        final Author savedAuthor = authorRepository.save(convertToAuthor(authorData));
        return new ResponseEntity<>(convertToAuthorData(savedAuthor), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthorData> getAuthor(final Long id) {
        final Optional<Author> author = authorRepository.findById(id);
        return ResponseEntity.ok(convertToAuthorData(author.orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        })));
    }

    @Override
    public ResponseEntity<AuthorData> updateAuthor(final AuthorData authorData) {
        return null;
    }

    private Author convertToAuthor(final AuthorData authorData) {
        return new Author.AuthorBuilder()
                .firstName(authorData.getFirstName())
                .secondName(authorData.getSecondName())
                .lastName(authorData.getLastName())
                .birthDate(authorData.getBirthDate())
                .deathDate(authorData.getDeathDate())
                .books(authorData.getBooks())
                .build();
    }

    private AuthorData convertToAuthorData(final Author author) {
        return new AuthorData.AuthorDataBuilder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .secondName(author.getSecondName())
                .lastName(author.getLastName())
                .birthDate(author.getBirthDate())
                .deathDate(author.getDeathDate())
                .books(author.getBooks())
                .build();
    }
}
