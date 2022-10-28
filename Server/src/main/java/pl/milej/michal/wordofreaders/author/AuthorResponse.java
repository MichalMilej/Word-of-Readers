package pl.milej.michal.wordofreaders.author;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class AuthorResponse {
    private long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthDate;
    private Date deathDate;
}
