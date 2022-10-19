package pl.milej.michal.wordofreaders.user;

public class UserConverter {

    public static UserResponse convertToUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .profilePhotoId(user.getProfilePhoto().getId())
                .banned(user.getBanned())
                .build();
    }
}
