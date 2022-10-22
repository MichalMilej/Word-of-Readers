package pl.milej.michal.wordofreaders.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameEqualsIgnoreCase(String username);
    Optional<User> findByEmailEqualsIgnoreCase(String email);
}
