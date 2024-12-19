package pl.milej.michal.worldofreaders.publisher;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {
}
