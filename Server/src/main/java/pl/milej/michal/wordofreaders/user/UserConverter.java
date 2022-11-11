package pl.milej.michal.wordofreaders.user;

public class UserConverter {

    public static UserResponse convertToUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .profilePhotoId(user.getProfilePhoto().getId())
                .banned(user.getBanned())
                .activated(user.getActivated())
                .build();
    }

    public static UserResponsePublic convertToUserResponsePublic(final User user) {
        return UserResponsePublic.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .profilePhotoId(user.getProfilePhoto().getId())
                .build();
    }
}
