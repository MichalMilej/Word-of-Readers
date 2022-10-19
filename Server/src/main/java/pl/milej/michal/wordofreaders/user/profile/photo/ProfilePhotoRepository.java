package pl.milej.michal.wordofreaders.user.profile.photo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePhotoRepository extends CrudRepository<ProfilePhoto, Long> {
}
