package pl.milej.michal.worldofreaders.book.cover;

import lombok.RequiredArgsConstructor;
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
    CoverResponse addCover(@RequestBody final MultipartFile coverImage) {
        return coverService.addCover(coverImage);
    }

    @GetMapping(value = "/{coverId}")
    CoverResponse getCover(@PathVariable final long coverId) {
        return coverService.getCover(coverId);
    }
}
