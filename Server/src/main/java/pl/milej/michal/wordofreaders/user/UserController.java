package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    final UserServiceImpl userService;

    @PostMapping
    UserResponse addStandardUser(@RequestBody final UserRequest userRequest) {
        return userService.addUser(userRequest, Role.STANDARD_USER);
    }

    @GetMapping("/{id}/profile-photo-image")
    FileSystemResource getUserProfilePhotoImage(@PathVariable final long id) {
        return userService.getUserProfilePhotoImage(id);
    }
}
