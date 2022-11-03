package pl.milej.michal.wordofreaders.review.reaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReactionRepository extends CrudRepository<UserReaction, Long> {
    Optional<UserReaction> findByUserIdAndReviewId(final Long userId, final Long reviewId);
}
