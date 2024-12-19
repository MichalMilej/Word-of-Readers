package pl.milej.michal.worldofreaders.user.profile.photo;

public class ProfilePhotoConverter {

    public static ProfilePhotoResponse convertToProfilePhotoResponse(final ProfilePhoto profilePhoto) {
        return ProfilePhotoResponse.builder()
                .id(profilePhoto.getId())
                .location(profilePhoto.getLocation())
                .build();
    }
}
