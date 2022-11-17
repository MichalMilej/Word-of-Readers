package pl.milej.michal.wordofreaders.publisher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class PublisherResponse {

    private Long id;
    private String name;
    @JsonIgnore
    private Set<Long> bookIds;
}
