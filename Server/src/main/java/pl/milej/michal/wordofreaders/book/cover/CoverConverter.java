package pl.milej.michal.wordofreaders.book.cover;

public class CoverConverter {

    public static CoverData convertCover(final Cover cover) {
        return new CoverData.CoverDataBuilder()
                .id(cover.getId())
                .coverName(cover.getCoverName())
                .location(cover.getLocation())
                .books(cover.getBooks())
                .build();
    }
}
