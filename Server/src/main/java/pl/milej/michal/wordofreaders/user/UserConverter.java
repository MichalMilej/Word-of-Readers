package pl.milej.michal.wordofreaders.user;

import pl.milej.michal.wordofreaders.user.profile.photo.ProfilePhotoConverter;

public class UserConverter {

    public static UserResponse convertToUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .profilePhotoResponse(ProfilePhotoConverter.convertToProfilePhotoResponse(user.getProfilePhoto()))
                .banned(user.getBanned())
                .activated(user.getActivated())
                .build();
    }

    public static UserResponsePublic convertToUserResponsePublic(final User user) {
        return UserResponsePublic.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .profilePhotoResponse(ProfilePhotoConverter.convertToProfilePhotoResponse(user.getProfilePhoto()))
                .build();
    }
}
