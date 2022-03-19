package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/")
public class MovieDatabaseController {

    private final MovieDatabaseService movieDatabaseService;

    public MovieDatabaseController(MovieDatabaseService movieDatabaseService) {
        this.movieDatabaseService = movieDatabaseService;
    }

    @GetMapping("/")
    public String get() {
        return "It's alive!";
    }

    @GetMapping("title/{tconst}")
    public Title getTitle(@PathVariable("tconst") String tconst) {
        System.out.println(tconst);
        if (tconst.equals("search")) return null;
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

    @GetMapping("title/search/{searchCategory}/{searchTerms}")
    public Page<TitleSearchResult> searchTitle(@PathVariable("searchCategory") String searchCategory ,@PathVariable("searchTerms") String searchTerms, Pageable pageable) {
        if (searchTerms == null) return null;
        return movieDatabaseService.searchTitle(searchCategory, searchTerms, pageable);
    }

}
