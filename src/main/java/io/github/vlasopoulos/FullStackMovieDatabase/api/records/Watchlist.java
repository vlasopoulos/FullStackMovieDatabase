package io.github.vlasopoulos.FullStackMovieDatabase.api.records;

public record Watchlist(
        String tconst,
        String primaryTitle,
        String titleType,
        float averageRating,
        int userRating,
        boolean watched
) {
}