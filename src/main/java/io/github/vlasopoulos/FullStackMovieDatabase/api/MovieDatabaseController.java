package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/")
public class MovieDatabaseController {

    private final MovieDatabaseService movieDatabaseService;

    public MovieDatabaseController(MovieDatabaseService movieDatabaseService) {
        this.movieDatabaseService = movieDatabaseService;
    }

    @GetMapping("title/{tconst}")
    public Title getTitle(@PathVariable("tconst") String tconst) {
        return movieDatabaseService.getTitle(tconst);
    }

    @GetMapping("person/{nconst}")
    public Person getPerson(@PathVariable("nconst") String nconst) {
        return movieDatabaseService.getPerson(nconst);
    }

    @GetMapping("person/multi")
    public List<Person> getPersons(@RequestParam List<String> nconst) {
        return movieDatabaseService.getPersons(nconst);
    }

    @GetMapping("principals/{tconst}")
    public List<Principal> getPrincipals(@PathVariable("tconst") String tconst) {
        return movieDatabaseService.getPrincipals(tconst);
    }

    @GetMapping("names")
    public List<Map<String,Object>> getNames(@RequestParam List<String> nconst){
        return movieDatabaseService.getNames(nconst);
    }

}
