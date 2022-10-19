package pl.milej.michal.wordofreaders.user.profile.photo;

import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.user.User;

import java.util.Set;

@Builder
@Data
public class ProfilePhotoData {
    private Long id;
    private String name;
    private String location;
    private Set<User> users;
}
