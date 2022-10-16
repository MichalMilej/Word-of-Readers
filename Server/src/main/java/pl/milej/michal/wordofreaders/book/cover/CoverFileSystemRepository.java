package pl.milej.michal.wordofreaders.book.cover;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import pl.milej.michal.wordofreaders.exception.SavingFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class CoverFileSystemRepository {

    @Value("${filesystem.path.covers}")
    private String directory;

    public String saveCoverImage(final MultipartFile coverImage) {
        final Path path = Paths.get(directory + new Date().getTime() + "-" + coverImage.getOriginalFilename());
        try {
            Files.write(path, coverImage.getBytes());
        } catch (IOException e) {
            throw new SavingFileException("There was a problem with saving cover image");
        }
        return path.toAbsolutePath().toString();
    }

    public FileSystemResource getCoverImage(final String location) {
        return new FileSystemResource(Paths.get(location));
    }
}
