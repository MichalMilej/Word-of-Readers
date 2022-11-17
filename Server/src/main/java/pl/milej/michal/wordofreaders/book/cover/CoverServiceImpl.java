package pl.milej.michal.wordofreaders.book.cover;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CoverServiceImpl implements CoverService {

    final CoverFileSystemRepository coverFileSystemRepository;
    final CoverRepository coverRepository;

    @Override
    public CoverData addCover(final MultipartFile coverImage) {
        final String location = coverFileSystemRepository.saveCoverImage(coverImage);
        final Cover cover = new Cover(coverImage.getOriginalFilename(), location);

        return CoverConverter.convertCover(coverRepository.save(cover));
    }

    @Override
    public FileSystemResource getCoverImage(final Long id) {
        final Cover cover = coverRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Cover not found");
        });

        return coverFileSystemRepository.getCoverImage(cover.getLocation());
    }

    public Cover findCoverById(final long coverId) {
        return coverRepository.findById(coverId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Cover not found");
        });
    }
}
