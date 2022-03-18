package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.stereotype.Service;

@Service
public class MovieDatabaseService {

    private final MovieDatabaseDAO movieDatabaseDAO;

    public MovieDatabaseService(MovieDatabaseDAO movieDatabaseDAO) {
        this.movieDatabaseDAO = movieDatabaseDAO;
    }

    public Title getTitle(String tconst) {
        return movieDatabaseDAO.selectMovieByTconst(tconst).orElseThrow(RuntimeException::new);
    }
}
