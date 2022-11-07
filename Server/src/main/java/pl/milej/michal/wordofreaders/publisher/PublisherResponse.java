package pl.milej.michal.wordofreaders.publisher;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class PublisherResponse {

    private Long id;
    private String name;
    private Set<Long> bookIds;
}
