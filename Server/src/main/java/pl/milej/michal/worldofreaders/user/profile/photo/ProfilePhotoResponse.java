package pl.milej.michal.worldofreaders.user.profile.photo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfilePhotoResponse {
    private long id;
    private String location;
}
