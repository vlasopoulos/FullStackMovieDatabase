package io.github.vlasopoulos.FullStackMovieDatabase.api.records;

import java.util.List;

public record Person(String nconst,
                     String primaryName,
                     int birthYear,
                     int deathYear,
                     List<String> primaryProfession,
                     List<String> knownForTitles
                     ) {
}
