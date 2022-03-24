package io.github.vlasopoulos.FullStackMovieDatabase.api.records;

public record Watched(
        String tconst,
        String primaryTitle,
        String titleType,
        float averageRating,
        int userRating,
        boolean watchlist
) {
}
