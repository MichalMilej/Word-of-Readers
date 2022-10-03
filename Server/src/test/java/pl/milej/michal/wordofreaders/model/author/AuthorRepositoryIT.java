package pl.milej.michal.wordofreaders.model.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.milej.michal.wordofreaders.BaseIT;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryIT extends BaseIT {
    @Autowired
    private AuthorRepository authorRepository;

    private final String firstName = "Steve";
    private final String lastName = "Longhead";

    @Test
    void shouldSaveAuthor() {
        // given
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        // when
        authorRepository.save(author);

        // then
        assertEquals(1, authorRepository.findAll().size());
        assertEquals(firstName, authorRepository.findAll().get(0).getFirstName());
        assertEquals(lastName, authorRepository.findAll().get(0).getLastName());
    }
}