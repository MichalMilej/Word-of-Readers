package pl.milej.michal.worldofreaders.book.cover;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CoverServiceImpl implements CoverService {

    final CoverFileSystemRepository coverFileSystemRepository;
    final CoverRepository coverRepository;

    @Override
    public CoverResponse addCover(final MultipartFile coverImage) {
        final String location = coverFileSystemRepository.saveCoverImage(coverImage);
        final Cover cover = new Cover(coverImage.getOriginalFilename(), location);

        return CoverConverter.convertToCoverResponse(coverRepository.save(cover));
    }

    @Override
    public CoverResponse getCover(final Long coverId) {
        final Cover cover = findCoverById(coverId);
        return CoverConverter.convertToCoverResponse(cover);
    }

    public Cover findCoverById(final long coverId) {
        return coverRepository.findById(coverId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Cover not found");
        });
    }
}
