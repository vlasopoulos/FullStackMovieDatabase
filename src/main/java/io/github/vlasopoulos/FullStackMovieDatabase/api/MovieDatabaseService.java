package io.github.vlasopoulos.FullStackMovieDatabase.api;

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

    public List<Map<String, Object>> getNames(List<String> nconsts) {
        return movieDatabaseDAO.selectNamesByNconsts(nconsts);
    }

    // search categories for now: movie tvSeries tvMiniSeries tvEpisode tvSpecial tvMovie short tvShort tvPilot videoGame video
    public Page<TitleSearchResult> searchTitle(String searchCategory, String searchTerms, Pageable pageable) {
        return movieDatabaseDAO.searchTitle(searchCategory, searchTerms.replace('+','|'), pageable);
    }
}
