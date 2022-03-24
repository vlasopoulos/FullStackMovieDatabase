package io.github.vlasopoulos.FullStackMovieDatabase.api;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieDatabaseDAO {
    Optional<Title> selectMovieByTconst(String tconst);
    Optional<Person> selectPersonByNconst(String nconst);
    List<Person> selectPersonsByNconsts(List<String> nconsts);
    List<Principal> selectPrincipalsByTconst(String tconst);
    List<Map<String, Object>> selectNamesByNconsts(List<String> nconst);
    List<Map<String, Object>> selectTitlesByTconsts(List<String> tconsts);
    Page<TitleSearchResult> searchTitle(String searchCategory, String searchTerms, Pageable pageable);
    Page<Person> searchPerson(String searchTerms, Pageable pageable);
    void addToWatchlist(String tconst);
    void addToWatched(String tconst);
    void addUserRating(String tconst, int rating);
    void modifyUserRating(String tconst, int rating);
    void removeUserRating(String tconst);
    void removeFromWatchlist(String tconst);
    void removeFromWatched(String tconst);
    Page<Watchlist> getWatchlist(Pageable pageable);
    Page<Watched> getWatched(Pageable pageable);
}
