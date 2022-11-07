package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse addStandardUser(@RequestBody final UserRequest userRequest) {
        return userService.addUser(userRequest, Role.STANDARD_USER);
    }

    @GetMapping("/{id}")
    UserResponse getUser(@PathVariable final long id) {
        return userService.getUser(id);
    }

    @GetMapping
    Page<UserResponse> getUsers(@RequestParam final Integer pageNumber, @RequestParam final Integer pageSize) {
        return userService.getUsers(pageNumber, pageSize);
    }

    @GetMapping("/{id}/profile-photo-image")
    FileSystemResource getUserProfilePhotoImage(@PathVariable final long id) {
        return userService.getUserProfilePhotoImage(id);
    }

    @PatchMapping("/{id}/profile-photo")
    UserResponse updateUserProfilePhoto(@PathVariable final long id, @RequestBody final MultipartFile newProfilePhotoImage) {
        return userService.updateUserProfilePhoto(id, newProfilePhotoImage);
    }
}
