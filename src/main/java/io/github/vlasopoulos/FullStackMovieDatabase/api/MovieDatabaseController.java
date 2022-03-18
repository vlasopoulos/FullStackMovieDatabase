package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/")
public class MovieDatabaseController {

    private final MovieDatabaseService movieDatabaseService;

    public MovieDatabaseController(MovieDatabaseService movieDatabaseService) {
        this.movieDatabaseService = movieDatabaseService;
    }

    @GetMapping("title/{tconst}")
    public Title getTitle(@PathVariable("tconst") String tconst) {
        return movieDatabaseService.getTitle(tconst);
    }

}
