package pl.milej.michal.wordofreaders.user.profile.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users/profile-photos")
@RequiredArgsConstructor
public class ProfilePhotoController {

    final ProfilePhotoServiceImpl profilePhotoService;

    @PostMapping
    ProfilePhotoData addProfilePhoto(@RequestBody final MultipartFile profilePhotoImage) {
        return profilePhotoService.addProfilePhoto(profilePhotoImage);
    }

    @GetMapping("/{id}")
    FileSystemResource getProfilePhotoImage(@PathVariable final long id) {
        return profilePhotoService.getProfilePhotoImage(id);
    }
}
