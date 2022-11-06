package pl.milej.michal.wordofreaders.book.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    Optional<UserScore> findByBookIdAndUserId(long bookId, long userId);
    @Query(value = "SELECT avg(score) from UserScore where book.id = ?1")
    Float calculateScoreAverage(final Long bookId);
}
