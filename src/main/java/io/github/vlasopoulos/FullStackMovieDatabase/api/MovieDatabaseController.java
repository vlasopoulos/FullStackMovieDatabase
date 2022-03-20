package io.github.vlasopoulos.FullStackMovieDatabase.api;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Person;
import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Principal;
import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Title;
import io.github.vlasopoulos.FullStackMovieDatabase.api.records.TitleSearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("")
    public String get() {
        return "It's alive!";
    }

    @GetMapping("title/{tconst}")
    public Title getTitle(@PathVariable("tconst") String tconst) {
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

    @GetMapping("person-names")
    public List<Map<String,Object>> getPersonNames(@RequestParam List<String> nconst){
        return movieDatabaseService.getPersonNames(nconst);
    }

    @GetMapping("title-names")
    public List<Map<String,Object>> getTitleNames(@RequestParam List<String> tconst){
        return movieDatabaseService.getTitleNames(tconst);
    }

    @GetMapping("title/search/{searchCategory}/{searchTerms}")
    public Page<TitleSearchResult> searchTitle(@PathVariable("searchCategory") String searchCategory , @PathVariable("searchTerms") String searchTerms, Pageable pageable) {
        if (searchTerms == null) return null;
        return movieDatabaseService.searchTitle(searchCategory, searchTerms, pageable);
    }

    @GetMapping("person/search/{searchTerms}")
    public Page<Person> searchPerson(@PathVariable("searchTerms") String searchTerms, Pageable pageable) {
        if (searchTerms == null) return null;
        return movieDatabaseService.searchPerson(searchTerms, pageable);
    }

    @PostMapping
    public void updateDatabase(@RequestBody String postString) {
        if (postString.equals("update-database"))
            movieDatabaseService.updateDatabase();
    }

}
