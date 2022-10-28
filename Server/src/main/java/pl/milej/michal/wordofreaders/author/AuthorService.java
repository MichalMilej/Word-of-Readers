package pl.milej.michal.wordofreaders.author;

public interface AuthorService {

    AuthorResponse addAuthor(final AuthorRequest authorRequest);
    AuthorResponse getAuthor(final long id);
    AuthorResponse updateAuthor(final long id, final AuthorRequest authorRequest);
    void deleteAuthor(final long id);
}
