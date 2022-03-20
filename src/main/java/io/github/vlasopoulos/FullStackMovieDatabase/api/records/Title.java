package io.github.vlasopoulos.FullStackMovieDatabase.api.records;

import java.util.List;
import java.util.Map;

public record Title(String tconst,
                    String titleType,
                    String primaryTitle,
                    String originalTitle,
                    boolean isAdult,
                    int startYear,
                    int endYear,
                    int runtimeMinutes,
                    List<String> genres,
                    List<String> directors,
                    List<String> writers,
                    float averageRating,
                    int numVotes
//                    ,List<Map<String,List<String>>> actors, //name - characters
//                    List<Map<String,String>> principals  //name - category
                    ) {
}
