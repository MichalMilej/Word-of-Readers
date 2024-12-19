package pl.milej.michal.worldofreaders.book.review.reaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactionRepository extends CrudRepository<Reaction, Long> {
    Optional<Reaction> findByReviewIdAndUserId(Long reviewId, Long userId);
    void deleteAllByReviewId(Long reviewId);
}
