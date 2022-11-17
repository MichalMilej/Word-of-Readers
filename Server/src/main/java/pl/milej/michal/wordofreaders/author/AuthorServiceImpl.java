package pl.milej.michal.wordofreaders.author;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.milej.michal.wordofreaders.exception.RequiredVariablesNotSetException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository authorRepository;

    @Override
    public AuthorResponse addAuthor(final AuthorRequest authorRequest) {
        if (!StringUtils.hasText(authorRequest.getFirstName()) || !StringUtils.hasText(authorRequest.getLastName())) {
            throw new RequiredVariablesNotSetException("Variable firstName or lastName has not been set");
        }
        final Author savedAuthor = authorRepository.save(AuthorConverter.convertAuthorRequestToAuthor(authorRequest));
        return AuthorConverter.convertAuthorToAuthorResponse(savedAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse getAuthor(final long id) {
        final Optional<Author> author = authorRepository.findById(id);
        return AuthorConverter.convertAuthorToAuthorResponse(author.orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        }));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuthorResponse> getAuthors(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return authorRepository.findAll(pageable).map(AuthorConverter::convertAuthorToAuthorResponse);
    }

    @Override
    public AuthorResponse updateAuthor(final long id, final AuthorRequest authorRequest) {
        final Author existingAuthor = authorRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        });

        if (StringUtils.hasText(authorRequest.getFirstName())) {
            existingAuthor.setFirstName(authorRequest.getFirstName());
        }
        existingAuthor.setSecondName(authorRequest.getSecondName());
        if (StringUtils.hasText(authorRequest.getLastName())) {
            existingAuthor.setLastName(authorRequest.getLastName());
        }
        existingAuthor.setBirthDate(authorRequest.getBirthDate());
        existingAuthor.setDeathDate(authorRequest.getDeathDate());

        return AuthorConverter.convertAuthorToAuthorResponse(authorRepository.save(existingAuthor));
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findAuthorById(long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Author not found");
        });
    }
}
