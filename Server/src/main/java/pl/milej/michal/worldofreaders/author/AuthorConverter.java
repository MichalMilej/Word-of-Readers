package pl.milej.michal.worldofreaders.author;

public class AuthorConverter {

    public static Author convertAuthorRequestToAuthor(final AuthorRequest authorRequest) {
        final Author author = new Author();
        author.setFirstName(authorRequest.getFirstName());
        author.setSecondName(authorRequest.getSecondName());
        author.setLastName(authorRequest.getLastName());
        author.setBirthDate(authorRequest.getBirthDate());
        author.setDeathDate(authorRequest.getDeathDate());
        return author;
    }

    public static AuthorResponse convertAuthorToAuthorResponse(final Author author) {
        return new AuthorResponse.AuthorResponseBuilder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .secondName(author.getSecondName())
                .lastName(author.getLastName())
                .birthDate(author.getBirthDate())
                .deathDate(author.getDeathDate())
                .build();
    }
}
