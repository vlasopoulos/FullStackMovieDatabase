package io.github.vlasopoulos.FullStackMovieDatabase.api.records;

import java.util.List;

public record TitleSearchResult(
        String tconst,
        String titleType,
        String primaryTitle,
        boolean isAdult,
        int startYear,
        int endYear,
        List<String> genres,
        float averateRating
) {
}
