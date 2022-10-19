package pl.milej.michal.wordofreaders.user;

import org.springframework.core.io.FileSystemResource;

public interface UserService {

    UserResponse addUser(final UserRequest userRequest, final Role role);
    FileSystemResource getUserProfilePhotoImage(final Long id);
}
