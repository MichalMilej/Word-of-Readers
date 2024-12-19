package pl.milej.michal.worldofreaders.author;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deathDate;
}
