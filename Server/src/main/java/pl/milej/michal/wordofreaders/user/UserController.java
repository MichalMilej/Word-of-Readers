package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
@Transactional
public class UserController {

    final UserServiceImpl userService;
    final UserAuthenticationService userAuthenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse addStandardUser(@RequestBody final UserRequest userRequest) {
        return userService.addUser(userRequest, UserRole.USER);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@userAuthenticationService.canPrincipalAccessUser(#userId)")
    UserResponse getUser(@PathVariable final long userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/public/user/{userId}")
    UserResponsePublic getUserPublic(@PathVariable final long userId) {
        return userService.getUserPublic(userId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('MOD', 'ADMIN')")
    Page<UserResponse> getUsers(@RequestParam final Integer pageNumber, @RequestParam final Integer pageSize) {
        return userService.getUsers(pageNumber, pageSize);
    }

    @GetMapping("/public/user/{userId}/profile-photo-image")
    FileSystemResource getUserProfilePhotoImage(@PathVariable final long userId) {
        return userService.getUserProfilePhotoImage(userId);
    }

    @PatchMapping("/{userId}/profile-photo")
    @PreAuthorize("@userAuthenticationService.canPrincipalAccessUser(#userId)")
    UserResponse updateUserProfilePhoto(@PathVariable final long userId, @RequestBody final MultipartFile newProfilePhotoImage) {
        return userService.updateUserProfilePhoto(userId, newProfilePhotoImage);
    }

    @PatchMapping("/{userId}/banned")
    @PreAuthorize("hasAuthority('ADMIN')")
    UserResponse updateUserBannedStatus(@PathVariable long userId, @RequestBody UserBannedRequest userBannedRequest) {
        return userService.updateUserBanned(userId, userBannedRequest);
    }

    @PatchMapping("/{userId}/user-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    UserResponse updateUserRole(@PathVariable final long userId, @RequestBody final UserRoleRequest userRoleRequest) {
        return userService.updateUserRole(userId, userRoleRequest);
    }
}
