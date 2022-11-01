package pl.milej.michal.wordofreaders.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUsernameEqualsIgnoreCase(String username);
    Optional<User> findByEmailEqualsIgnoreCase(String email);
}
