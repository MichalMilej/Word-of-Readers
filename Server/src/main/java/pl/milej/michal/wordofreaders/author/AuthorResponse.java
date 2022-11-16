package pl.milej.michal.wordofreaders.author;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@Builder
public class AuthorResponse {
    private long id;
    private String firstName;
    private String secondName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deathDate;
}
