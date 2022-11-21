package pl.milej.michal.wordofreaders.author;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AuthorRequest {

    private String firstName;
    private String secondName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deathDate;
}
