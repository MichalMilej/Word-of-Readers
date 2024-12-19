package pl.milej.michal.worldofreaders.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    final PublisherRepository publisherRepository;
    final PublisherValidator publisherValidator;

    @Override
    public PublisherResponse addPublisher(final PublisherRequest publisherRequest) {
        publisherValidator.validatePublisherRequest(publisherRequest);

        Publisher publisher = new Publisher();
        publisher.setName(publisherRequest.getName());

        return PublisherConverter.convertToPublisherResponse(publisherRepository.save(publisher));
    }

    @Override
    public PublisherResponse getPublisher(long publisherId) {
        return PublisherConverter.convertToPublisherResponse(findPublisherById(publisherId));
    }

    @Override
    public Page<PublisherResponse> getPublishers(int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Publisher> publishers = publisherRepository.findAll(pageable);

        return publishers.map(PublisherConverter::convertToPublisherResponse);
    }

    @Override
    public PublisherResponse updatePublisher(final long publisherId, final PublisherRequest publisherRequest) {
        publisherValidator.validatePublisherRequest(publisherRequest);

        final Publisher publisher = findPublisherById(publisherId);
        publisher.setName(publisherRequest.getName());

        return PublisherConverter.convertToPublisherResponse(publisherRepository.save(publisher));
    }

    public Publisher findPublisherById(final long publisherId) {
        return publisherRepository.findById(publisherId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Publisher not found");
        });
    }
}
