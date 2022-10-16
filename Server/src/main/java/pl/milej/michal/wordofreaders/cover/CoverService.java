package pl.milej.michal.wordofreaders.cover;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public interface CoverService {

    CoverData addCover(final MultipartFile coverImage);
    FileSystemResource getCoverImage(final Long id);
}
