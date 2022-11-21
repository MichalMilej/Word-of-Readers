package pl.milej.michal.wordofreaders.user.profile.photo;

public class ProfilePhotoConverter {

    public static ProfilePhotoResponse convertToProfilePhotoResponse(final ProfilePhoto profilePhoto) {
        return ProfilePhotoResponse.builder()
                .id(profilePhoto.getId())
                .location(profilePhoto.getLocation())
                .build();
    }
}
