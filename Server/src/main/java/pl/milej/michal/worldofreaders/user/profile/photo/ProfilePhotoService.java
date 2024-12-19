package pl.milej.michal.worldofreaders.user.profile.photo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePhotoService {
    ProfilePhoto addProfilePhoto(final MultipartFile profilePhotoImage);
    FileSystemResource getProfilePhotoImage(final Long profilePhotoId);
    void deleteProfilePhoto(final Long profilePhotoId);
}
