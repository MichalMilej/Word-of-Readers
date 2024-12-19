package pl.milej.michal.worldofreaders.user.profile.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfilePhotoServiceImpl implements ProfilePhotoService{

    final ProfilePhotoFileSystemRepository profilePhotoFileSystemRepository;
    final ProfilePhotoRepository profilePhotoRepository;
    
    @Override
    public ProfilePhoto addProfilePhoto(final MultipartFile profilePhotoImage) {
        final String location = profilePhotoFileSystemRepository.saveProfilePhoto(profilePhotoImage);
        final ProfilePhoto profilePhoto = new ProfilePhoto(location);

        return profilePhotoRepository.save(profilePhoto);
    }

    @Override
    public FileSystemResource getProfilePhotoImage(final Long profilePhotoId) {
        final ProfilePhoto photo = findProfilePhotoById(profilePhotoId);

        return profilePhotoFileSystemRepository.getProfilePhoto(photo.getLocation());
    }

    @Override
    public void deleteProfilePhoto(final Long profilePhotoId) {
        final ProfilePhoto photo = findProfilePhotoById(profilePhotoId);

        profilePhotoFileSystemRepository.deleteProfilePhoto(photo.getLocation());
        profilePhotoRepository.delete(photo);
    }

    public ProfilePhoto findProfilePhotoById(final Long profilePhotoId) {
        return profilePhotoRepository.findById(profilePhotoId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Profile photo not found");
        });
    }
}
