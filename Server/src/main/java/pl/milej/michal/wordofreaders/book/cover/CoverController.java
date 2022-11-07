package pl.milej.michal.wordofreaders.book.cover;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books/covers")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@Transactional
public class CoverController {

    final CoverServiceImpl coverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CoverData addCover(@RequestBody final MultipartFile coverImage) {
        return coverService.addCover(coverImage);
    }

    @GetMapping(value = "/{id}/image")
    FileSystemResource getCoverImage(@PathVariable final long id) {
        return coverService.getCoverImage(id);
    }
}
