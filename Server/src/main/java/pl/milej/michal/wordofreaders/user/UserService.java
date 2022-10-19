package pl.milej.michal.wordofreaders.user;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserResponse addUser(final UserRequest userRequest, final Role role);
    UserResponse getUser(final Long id);
    FileSystemResource getUserProfilePhotoImage(final Long id);
    UserResponse updateUserProfilePhoto(final Long id, final MultipartFile profilePhotoImage);
}
