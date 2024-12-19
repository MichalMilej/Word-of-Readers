package pl.milej.michal.worldofreaders.user;

import lombok.Builder;
import lombok.Data;
import pl.milej.michal.worldofreaders.user.profile.photo.ProfilePhotoResponse;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private UserRole userRole;
    private ProfilePhotoResponse profilePhotoResponse;
    private boolean banned;
    private boolean activated;
}
