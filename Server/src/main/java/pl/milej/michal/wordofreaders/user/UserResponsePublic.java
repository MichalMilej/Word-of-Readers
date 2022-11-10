package pl.milej.michal.wordofreaders.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponsePublic {

    private Long id;
    private String username;
    private Long profilePhotoId;
}
