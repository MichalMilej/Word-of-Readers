package pl.milej.michal.worldofreaders.book.genre;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenreResponse {

    private Long id;
    private String name;
}
