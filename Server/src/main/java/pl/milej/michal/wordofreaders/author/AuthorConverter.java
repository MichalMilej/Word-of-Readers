package pl.milej.michal.wordofreaders.author;

public class AuthorConverter {

    public static Author convertToAuthor(final AuthorData authorData) {
        final Author author = new Author();
        author.setFirstName(authorData.getFirstName());
        author.setSecondName(authorData.getSecondName());
        author.setLastName(authorData.getLastName());
        author.setBirthDate(authorData.getBirthDate());
        author.setDeathDate(authorData.getDeathDate());
        author.setBooks(authorData.getBooks());
        return author;
    }

    public static AuthorData convertToAuthorData(final Author author) {
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
