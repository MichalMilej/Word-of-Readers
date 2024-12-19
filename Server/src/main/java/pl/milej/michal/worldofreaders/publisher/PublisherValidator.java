package pl.milej.michal.worldofreaders.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.milej.michal.worldofreaders.exception.BadRequestException;

@Component
@RequiredArgsConstructor
public class PublisherValidator {

    final PublisherRepository publisherRepository;

    public void validatePublisherRequest(final PublisherRequest publisherRequest) {
        if (!StringUtils.hasText(publisherRequest.getName())) {
            throw new BadRequestException("Publisher name is empty");
        }
    }
}
