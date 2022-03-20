package io.github.vlasopoulos.FullStackMovieDatabase.api.records;

import java.util.List;

public record Principal(
        String tconst,
        int ordering,
        String nconst,
        String category,
        String job,
        List<String> characters
) {
}
