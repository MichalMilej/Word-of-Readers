package pl.milej.michal.wordofreaders.user.profile.photo;

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
    public FileSystemResource getProfilePhotoImage(final Long id) {
        final ProfilePhoto photo = profilePhotoRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Cover not found");
        });

        return profilePhotoFileSystemRepository.getProfilePhoto(photo.getLocation());
    }
}
