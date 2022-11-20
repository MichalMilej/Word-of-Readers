package pl.milej.michal.wordofreaders.book.cover;

public class CoverConverter {

    public static CoverResponse convertToCoverResponse(final Cover cover) {
        return new CoverResponse.CoverResponseBuilder()
                .id(cover.getId())
                .name(cover.getName())
                .location(cover.getLocation())
                .build();
    }
}
