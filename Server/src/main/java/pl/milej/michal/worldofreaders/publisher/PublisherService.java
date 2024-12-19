package pl.milej.michal.worldofreaders.publisher;

import org.springframework.data.domain.Page;

public interface PublisherService {
    PublisherResponse addPublisher(final PublisherRequest publisherRequest);
    PublisherResponse getPublisher(final long publisherId);
    Page<PublisherResponse> getPublishers(final int pageNumber, final int pageSize);
    PublisherResponse updatePublisher(final long publisherId, final PublisherRequest publisherRequest);
    Publisher findPublisherById(final long publisherId);
}
