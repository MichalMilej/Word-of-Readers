package pl.milej.michal.wordofreaders.author;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository authorRepository;

    @Override
    public AuthorData addAuthor(final AuthorData authorData) {
        if (!StringUtils.hasText(authorData.getFirstName()) || !StringUtils.hasText(authorData.getLastName())) {
            throw new RequiredVariablesNotSetException("Variable firstName or lastName has not been set");
        }
        System.out.println("Here: " + AuthorConverter.convertToAuthor(authorData).getLastName());
        final Author savedAuthor = authorRepository.save(AuthorConverter.convertToAuthor(authorData));
        return AuthorConverter.convertToAuthorData(savedAuthor);
    }

    @Override
    public AuthorData getAuthor(final long id) {
        final Optional<Author> author = authorRepository.findById(id);
        return AuthorConverter.convertToAuthorData(author.orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        }));
    }

    @Override
    public AuthorData updateAuthor(final long id, final AuthorData authorData) {
        final Author existingAuthor = authorRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        });

        if (StringUtils.hasText(authorData.getFirstName())) {
            existingAuthor.setFirstName(authorData.getFirstName());
        }
        existingAuthor.setSecondName(authorData.getSecondName());
        if (StringUtils.hasText(authorData.getLastName())) {
            existingAuthor.setLastName(authorData.getLastName());
        }
        existingAuthor.setBirthDate(authorData.getBirthDate());
        existingAuthor.setDeathDate(authorData.getDeathDate());
        existingAuthor.setBooks(authorData.getBooks());

        return AuthorConverter.convertToAuthorData(authorRepository.save(existingAuthor));
    }

    @Override
    public void deleteAuthor(long id) {
        final Author existingAuthor = authorRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        });

        authorRepository.delete(existingAuthor);
    }
}
