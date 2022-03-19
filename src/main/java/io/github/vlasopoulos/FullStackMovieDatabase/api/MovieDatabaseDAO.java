package io.github.vlasopoulos.FullStackMovieDatabase.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieDatabaseDAO {
    Optional<Title> selectMovieByTconst(String tconst);
    Optional<Person> selectPersonByNconst(String nconst);
    List<Person> selectPersonsByNconsts(List<String> nconsts);
    List<Principal> selectPrincipalsByTconst(String tconst);
    List<Map<String, Object>> selectNamesByNconsts(List<String> nconst);
}
