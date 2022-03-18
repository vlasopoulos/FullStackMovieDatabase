package io.github.vlasopoulos.FullStackMovieDatabase.api;

import java.util.List;
import java.util.Map;

public class Title{
    private  String tconst;
    private  String titleType;
    private  String primaryTitle;
    private  String originalTitle;
    private  boolean isAdult;
    private  int startYear;
    private  int endYear;
    private  int runtimeMinutes;
    private  List<String> genres = List.of();
    private  List<String> directors = List.of();
    private  List<String> writers = List.of();
    private  float averageRating;
    private  int numVotes;

    public Title(String tconst, String titleType, String primaryTitle, String originalTitle, boolean isAdult, int startYear, int endYear, int runtimeMinutes, List<String> genres, List<String> directors, List<String> writers, float averageRating, int numVotes) {
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        if (genres != null)
        this.genres = genres;
        if (directors != null)
        this.directors = directors;
        if (writers != null)
        this.writers = writers;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }
}
