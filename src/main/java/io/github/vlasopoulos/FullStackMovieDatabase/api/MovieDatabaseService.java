package io.github.vlasopoulos.FullStackMovieDatabase.api;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.*;
import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.IMDBDataDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieDatabaseService {
    private boolean canUpdate = true;
    private Thread imdbDataDownloaderThread;

    @Autowired
    IMDBDataDownloader imdbDataDownloader;

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
        String searchCategoryProcessed = switch (searchCategory) {
            case "tv-series" -> "'tvSeries' OR title_type = 'tvMiniSeries'";
            case "short" -> "'short' OR title_type = 'tvShort'";
            case "any" -> "title_type";
            default -> "'" + searchCategory + "'";
        };
        return movieDatabaseDAO.searchTitle(searchCategoryProcessed, searchTerms.replace('+','&'), pageable);
    }

    public Page<Person> searchPerson(String searchTerms, Pageable pageable) {
        return movieDatabaseDAO.searchPerson(searchTerms.replace('+','&'), pageable);
    }

    public void updateDatabase(){
        setCanUpdate(false);
        System.out.println("Starting imdbDataDownloader");
		imdbDataDownloaderThread = imdbDataDownloader;
		imdbDataDownloaderThread.start();
		System.out.println("Started imdbDataDownloaderThread");
    }

    public void addToWatchlist(String tconst) {
        movieDatabaseDAO.addToWatchlist(tconst);
    }

    public void addToWatched(String tconst) {
        movieDatabaseDAO.addToWatched(tconst);
    }

    public void addUserRating(String tconst, int rating) {
        movieDatabaseDAO.addUserRating(tconst, rating);
    }

    public void modifyUserRating(String tconst, int rating) {
        movieDatabaseDAO.modifyUserRating(tconst, rating);
    }

    public void removeUserRating(String tconst) {
        movieDatabaseDAO.removeUserRating(tconst);
    }

    public void removeFromWatchlist(String tconst) {
        movieDatabaseDAO.removeFromWatchlist(tconst);
    }

    public void removeFromWatched(String tconst) {
        movieDatabaseDAO.removeFromWatched(tconst);
    }

    public Page<Watchlist> getWatchlist(Pageable pageable) {
        return movieDatabaseDAO.getWatchlist(pageable);
    }

    public Page<Watched> getWatched(Pageable pageable) {
        return movieDatabaseDAO.getWatched(pageable);
    }

    public boolean getCanUpdate() {
        if(imdbDataDownloaderThread == null || imdbDataDownloaderThread.getState()==Thread.State.TERMINATED || imdbDataDownloaderThread.getState()==Thread.State.NEW) {
            setCanUpdate(true);
        } else {
            setCanUpdate(false);
        }
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }
}
