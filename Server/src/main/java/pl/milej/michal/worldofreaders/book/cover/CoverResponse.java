package pl.milej.michal.worldofreaders.book.cover;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoverResponse {
    private Long id;
    private String name;
    private String location;
}
