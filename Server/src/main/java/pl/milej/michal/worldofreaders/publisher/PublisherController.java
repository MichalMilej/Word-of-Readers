package pl.milej.michal.worldofreaders.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
@Transactional
@ResponseStatus(HttpStatus.OK)
public class PublisherController {

    final PublisherServiceImpl publisherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PublisherResponse addPublisher(@RequestBody PublisherRequest publisherRequest) {
        return publisherService.addPublisher(publisherRequest);
    }

    @GetMapping("/{publisherId}")
    PublisherResponse getPublisher(@PathVariable long publisherId) {
        return publisherService.getPublisher(publisherId);
    }

    @GetMapping
    Page<PublisherResponse> getPublishers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return publisherService.getPublishers(pageNumber, pageSize);
    }

    @PatchMapping("/{publisherId}")
    PublisherResponse updatePublisher(@PathVariable long publisherId, @RequestBody PublisherRequest publisherRequest) {
        return publisherService.updatePublisher(publisherId, publisherRequest);
    }
}
