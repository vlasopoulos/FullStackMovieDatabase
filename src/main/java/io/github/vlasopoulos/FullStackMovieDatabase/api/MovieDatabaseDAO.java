package io.github.vlasopoulos.FullStackMovieDatabase.api;

import java.util.Optional;

public interface MovieDatabaseDAO {
    Optional<Title> selectMovieByTconst(String tconst);
}
