package pl.milej.michal.wordofreaders.user;

import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public interface UserService {

    UserResponse addUser(final UserRequest userRequest, final UserRole userRole);
    UserResponse getUser(final long id);
    UserResponsePublic getUserPublic(final long userId);
    Page<UserResponse> getUsers(final Integer pageNumber, final Integer pageSize);
    FileSystemResource getUserProfilePhotoImage(final long id);
    UserResponse updateUserProfilePhoto(final long id, final MultipartFile profilePhotoImage);
    UserResponse updateUserRole(final long userId, final UserRoleRequest userRoleRequest);
    UserResponse updateUserBanned(final long userId, final UserBannedRequest userBannedRequest);
    User findUserById(final long id);
}
