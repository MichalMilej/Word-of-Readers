package pl.milej.michal.wordofreaders.user.profile.photo;

public class ProfilePhotoConverter {

    public static ProfilePhotoData convertProfilePhoto(final ProfilePhoto photo) {
        return new ProfilePhotoData.ProfilePhotoDataBuilder()
                .id(photo.getId())
                .name(photo.getName())
                .location(photo.getLocation())
                .users(photo.getUsers())
                .build();
    }
}