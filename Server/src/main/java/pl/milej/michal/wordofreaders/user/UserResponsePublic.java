package pl.milej.michal.wordofreaders.user;

import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.user.profile.photo.ProfilePhotoResponse;

@Data
@Builder
public class UserResponsePublic {

    private Long id;
    private String username;
    private UserRole userRole;
    private ProfilePhotoResponse profilePhotoResponse;
}
