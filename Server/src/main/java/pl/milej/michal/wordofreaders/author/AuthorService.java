package pl.milej.michal.wordofreaders.author;

import org.springframework.data.domain.Page;

public interface AuthorService {

    AuthorResponse addAuthor(final AuthorRequest authorRequest);
    AuthorResponse getAuthor(final long id);
    Page<AuthorResponse> getAuthors(final Integer pageNumber, final Integer pageSize);
    AuthorResponse updateAuthor(final long id, final AuthorRequest authorRequest);
    void deleteAuthor(final long id);
}
