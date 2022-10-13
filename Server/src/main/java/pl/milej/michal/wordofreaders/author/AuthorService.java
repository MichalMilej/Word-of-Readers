package pl.milej.michal.wordofreaders.author;

public interface AuthorService {

    AuthorData addAuthor(final AuthorData authorData);
    AuthorData getAuthor(final long id);
    AuthorData updateAuthor(final long id, final AuthorData authorData);
    void deleteAuthor(final long id);
}
