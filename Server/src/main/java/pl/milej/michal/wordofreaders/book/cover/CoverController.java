package pl.milej.michal.wordofreaders.book.cover;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books/covers")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class CoverController {

    final CoverServiceImpl coverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CoverData addCover(@RequestBody final MultipartFile coverImage) {
        return coverService.addCover(coverImage);
    }

    @GetMapping(value = "/{id}")
    FileSystemResource getCoverImage(@PathVariable final long id) {
        return coverService.getCoverImage(id);
    }
}
