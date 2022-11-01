package pl.milej.michal.wordofreaders.user;

import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserResponse addUser(final UserRequest userRequest, final Role role);
    UserResponse getUser(final Long id);
    Page<UserResponse> getUsers(final Integer pageNumber, final Integer pageSize);
    FileSystemResource getUserProfilePhotoImage(final Long id);
    UserResponse updateUserProfilePhoto(final Long id, final MultipartFile profilePhotoImage);
}
