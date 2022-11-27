package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.milej.michal.wordofreaders.exception.RequiredResourceNotInDatabaseException;
import pl.milej.michal.wordofreaders.user.profile.photo.*;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserRequestValidator userRequestValidator;
    final UserRepository userRepository;
    final ProfilePhotoRepository profilePhotoRepository;
    final ProfilePhotoServiceImpl profilePhotoService;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = findUserByUsernameEqualsIgnoreCase(username);
        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());

        return new UserPrincipal(user.getUsername(), user.getHashedPassword(), Collections.singletonList(authority), user);
    }

    @Override
    public UserResponse addUser(final UserRequest userRequest, final UserRole userRole) {
        userRequestValidator.validateUserRequest(userRequest);
        final User savedUser = saveUser(userRequest, userRole);
        log.info(String.format("User with id %s created", savedUser.getId()));

        return UserConverter.convertToUserResponse(savedUser);
    }

    private User saveUser(final UserRequest userRequest, final UserRole userRole) {
        final User newUser = new User();

        newUser.setUsername(userRequest.getUsername());
        newUser.setHashedPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setEmail(userRequest.getEmail());
        newUser.setUserRole(userRole);
        final ProfilePhoto defaultProfilePhoto = profilePhotoRepository.findById(1L).orElseThrow(() -> {
            throw new RequiredResourceNotInDatabaseException("Default user profile photo not saved to database");
        });
        newUser.setProfilePhoto(defaultProfilePhoto);

        return userRepository.save(newUser);
    }

    @Override
    public UserResponse getUser(final Long userId) {
        return UserConverter.convertToUserResponse(findUserById(userId));
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return UserConverter.convertToUserResponse(findUserByUsernameEqualsIgnoreCase(username));
    }

    @Override
    public Page<UserResponse> getUsers(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable).map(UserConverter::convertToUserResponse);
    }

    @Override
    public UserResponsePublic getUserPublic(final long userId) {
        return UserConverter.convertToUserResponsePublic(findUserById(userId));
    }

    @Override
    public FileSystemResource getUserProfilePhotoImage(final long userId) {
        final User user = findUserById(userId);
        final ProfilePhoto photo = profilePhotoRepository.findById(user.getProfilePhoto().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("User profile photo not found in database");
        });
        return profilePhotoService.getProfilePhotoImage(photo.getId());
    }

    @Override
    public UserResponse updateUserProfilePhoto(final long id, final MultipartFile profilePhotoImage) {
        final User existingUser = findUserById(id);

        final ProfilePhoto newProfilePhoto = profilePhotoService.addProfilePhoto(profilePhotoImage);
        existingUser.setProfilePhoto(newProfilePhoto);

        return UserConverter.convertToUserResponse(userRepository.save(existingUser));
    }

    @Override
    public UserResponse updateUserRole(final long userId, final UserRoleRequest userRoleRequest) {
        final User existingUser = findUserById(userId);
        existingUser.setUserRole(userRoleRequest.getUserRole());

        return UserConverter.convertToUserResponse(userRepository.save(existingUser));
    }

    @Override
    public UserResponse updateUserBanned(long userId, UserBannedRequest userBannedRequest) {
        final User user = findUserById(userId);
        user.setBanned(userBannedRequest.getBanned());
        return UserConverter.convertToUserResponse(userRepository.save(user));
    }

    public User findUserById(final long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("User not found");
        });
    }

    public User findUserByUsernameEqualsIgnoreCase(final String username) {
        return userRepository.findByUsernameEqualsIgnoreCase(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
    }
}
