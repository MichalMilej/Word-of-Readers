package pl.milej.michal.wordofreaders.user.profile.photo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePhotoService {
    ProfilePhotoData addProfilePhoto(MultipartFile profilePhotoImage);
    FileSystemResource getProfilePhotoImage(final Long id);
}
