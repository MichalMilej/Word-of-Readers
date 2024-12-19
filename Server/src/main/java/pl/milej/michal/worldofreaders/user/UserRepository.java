package pl.milej.michal.worldofreaders.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameEqualsIgnoreCase(String username);
    Optional<User> findByEmailEqualsIgnoreCase(String email);
}
