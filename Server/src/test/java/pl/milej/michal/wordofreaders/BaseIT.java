package pl.milej.michal.wordofreaders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.milej.michal.wordofreaders.model.author.Author;
import pl.milej.michal.wordofreaders.model.author.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class BaseIT {

    @Container
    protected static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest");

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

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
