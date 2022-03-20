package io.github.vlasopoulos.FullStackMovieDatabase.api;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Person;
import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Principal;
import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Title;
import io.github.vlasopoulos.FullStackMovieDatabase.api.records.TitleSearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieDatabaseService {

    private final MovieDatabaseDAO movieDatabaseDAO;

    public MovieDatabaseService(MovieDatabaseDAO movieDatabaseDAO) {
        this.movieDatabaseDAO = movieDatabaseDAO;
    }

    public Title getTitle(String tconst) {
        return movieDatabaseDAO.selectMovieByTconst(tconst).orElseThrow(RuntimeException::new);
    }

    public Person getPerson(String nconst) {
        return movieDatabaseDAO.selectPersonByNconst(nconst).orElseThrow(RuntimeException::new);
    }

    public List<Person> getPersons(List<String> nconsts) {
        return movieDatabaseDAO.selectPersonsByNconsts(nconsts);
    }

    public List<Principal> getPrincipals(String tconst) {
        return movieDatabaseDAO.selectPrincipalsByTconst(tconst);
    }

    public List<Map<String, Object>> getPersonNames(List<String> nconsts) {
        return movieDatabaseDAO.selectNamesByNconsts(nconsts);
    }

    public List<Map<String, Object>> getTitleNames(List<String> tconsts) {
        return movieDatabaseDAO.selectTitlesByTconsts(tconsts);
    }

    // search categories for now: movie tvSeries tvMiniSeries tvEpisode tvSpecial tvMovie short tvShort tvPilot videoGame video
    public Page<TitleSearchResult> searchTitle(String searchCategory, String searchTerms, Pageable pageable) {
        String searchCategoryProcessed;
        switch (searchCategory){
            case "movie":
                searchCategoryProcessed = "movie' OR title_type = 'tvMovie";
                break;
            case "tv-series":
                searchCategoryProcessed = "tvSeries' OR title_type = 'tvMiniSeries";
                break;
            case "short":
                searchCategoryProcessed = "short' OR title_type = 'tvShort";
                break;
            default:
                searchCategoryProcessed = searchCategory;
        }
        return movieDatabaseDAO.searchTitle(searchCategoryProcessed, searchTerms.replace('+','|'), pageable);
    }

    public Page<Person> searchPerson(String searchTerms, Pageable pageable) {
        return movieDatabaseDAO.searchPerson(searchTerms.replace('+','|'), pageable);
    }
}
