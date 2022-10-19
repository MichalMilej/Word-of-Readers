package pl.milej.michal.wordofreaders.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsernameEqualsIgnoreCase(String username);
    List<User> findByEmailEqualsIgnoreCase(String email);
}
