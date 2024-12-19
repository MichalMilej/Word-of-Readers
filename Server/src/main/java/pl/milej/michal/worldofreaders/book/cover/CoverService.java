package pl.milej.michal.worldofreaders.book.cover;

import org.springframework.web.multipart.MultipartFile;

public interface CoverService {

    CoverResponse addCover(final MultipartFile coverImage);
    CoverResponse getCover(final Long id);
    Cover findCoverById(final long coverId);
}
