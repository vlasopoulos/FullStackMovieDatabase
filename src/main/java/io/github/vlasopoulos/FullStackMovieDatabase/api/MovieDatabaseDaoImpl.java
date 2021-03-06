package io.github.vlasopoulos.FullStackMovieDatabase.api;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.*;
import io.github.vlasopoulos.FullStackMovieDatabase.api.rowMappers.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieDatabaseDaoImpl implements MovieDatabaseDAO {

    private final JdbcTemplate jdbcTemplate;

    public MovieDatabaseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Title> selectMovieByTconst(String tconst) {
        String sql = """
                SELECT b.tconst
                    , b.title_type
                    , b.primary_title
                    , b.original_title
                    , b.is_adult
                    , b.start_year
                    , b.end_year
                    , b.runtime_minutes
                    , b.genres
                    , c.directors
                    , c.writers
                    , r.average_rating
                    , r.num_votes
                    , watched.tconst AS watched
                    , watchlist.tconst AS watchlist
                    , ur.user_rating
               FROM title_basics b
                LEFT JOIN title_ratings r ON b.tconst = r.tconst
                LEFT JOIN title_crew c on b.tconst = c.tconst
                LEFT JOIN user_watched watched ON b.tconst = watched.tconst
                LEFT JOIN user_watchlist watchlist ON b.tconst = watchlist.tconst
                LEFT JOIN user_ratings ur ON b.tconst = ur.tconst
               WHERE b.tconst = ?;
                """;
        return jdbcTemplate.query(sql, new TitleRowMapper(), tconst).stream().findFirst();
    }

    @Override
    public Optional<Person> selectPersonByNconst(String nconst) {
        String sql = """
                SELECT * FROM name_basics
                WHERE nconst = ?;
                """;
        return jdbcTemplate.query(sql, new PersonRowMapper(), nconst).stream().findFirst();
    }

    @Override
    public List<Person> selectPersonsByNconsts(List<String> nconsts) {
        String sqlVariable = String.join(",", Collections.nCopies(nconsts.size(),"?"));
        String sql = String.format("SELECT * FROM name_basics WHERE nconst IN (%s)",sqlVariable);
        return jdbcTemplate.query(sql, new PersonRowMapper(), nconsts.toArray());
    }

    @Override
    public List<Principal> selectPrincipalsByTconst(String tconst) {
        String sql = """
                SELECT * FROM title_principals
                WHERE tconst = ?
                ORDER BY ordering
                """;
        return jdbcTemplate.query(sql, new PrincipalsRowMapper(), tconst);
    }

    @Override
    public List<Map<String, Object>> selectNamesByNconsts(List<String> nconsts) {
        String sqlVariable = String.join(",", Collections.nCopies(nconsts.size(),"?"));
        String sql = String.format("SELECT nconst, primary_name FROM name_basics WHERE nconst IN (%s)",sqlVariable);
        return jdbcTemplate.queryForList(sql,nconsts.toArray());
    }

    @Override
    public List<Map<String, Object>> selectTitlesByTconsts(List<String> tconsts) {
        String sqlVariable = String.join(",", Collections.nCopies(tconsts.size(),"?"));
        String sql = String.format("SELECT tconst, primary_title FROM title_basics WHERE tconst IN (%s)",sqlVariable);
        return jdbcTemplate.queryForList(sql,tconsts.toArray());
    }

    @Override
    public Page<TitleSearchResult> searchTitle(String searchCategory, String searchTerms, Pageable pageable) {
        String rowCountSQL = "SELECT count(1) AS row_count " +
                             "FROM title_basics " +
                             "WHERE title_ts @@ to_tsquery('english','" + searchTerms + "') AND (title_type=" + searchCategory + ");";

        int rowCount = jdbcTemplate.queryForObject(rowCountSQL,(rs, rowNum) -> rs.getInt(1));

        String sql = "SELECT title_basics.tconst, title_type, primary_title, is_adult, start_year, end_year, genres, average_rating, watched.tconst AS watched, watchlist.tconst AS watchlist, ur.user_rating " +
                "FROM title_basics " +
                "LEFT JOIN title_ratings ON title_basics.tconst = title_ratings.tconst " +
                "LEFT JOIN user_watched watched  ON title_basics.tconst = watched.tconst " +
                "LEFT JOIN user_watchlist watchlist ON title_basics.tconst = watchlist.tconst " +
                "LEFT JOIN user_ratings ur ON title_basics.tconst = ur.tconst " +
                "WHERE title_ts @@ to_tsquery('english','" + searchTerms + "') AND (title_type=" + searchCategory + ") " +
                "ORDER BY ts_rank(title_ts, to_tsquery('english','" + searchTerms.replace('&','|') + "')) DESC, title_basics.tconst " +
                "LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset() + ";";

        List<TitleSearchResult> searchResults = jdbcTemplate.query(sql, new TitleSearchResultRowMapper());

        return new PageImpl<>(searchResults, pageable, rowCount);
    }

    @Override
    public Page<Person> searchPerson(String searchTerms, Pageable pageable) {
        String rowCountSQL = "SELECT count(1) AS row_count " +
                "FROM name_basics " +
                "WHERE name_ts @@ to_tsquery('english','" + searchTerms + "');";

        int rowCount = jdbcTemplate.queryForObject(rowCountSQL,(rs, rowNum) -> rs.getInt(1));

        String sql = "SELECT nconst, primary_name, birth_year, death_year, primary_profession, known_for_titles " +
                "FROM name_basics " +
                "WHERE name_ts @@ to_tsquery('english','" + searchTerms + "')" +
                "ORDER BY ts_rank(name_ts, to_tsquery('english','" + searchTerms.replace('&','|') + "')) DESC, name_basics.nconst " +
                "LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset() + ";";

        List<Person> searchResults = jdbcTemplate.query(sql, new PersonRowMapper());

        return new PageImpl<>(searchResults, pageable, rowCount);
    }

    @Override
    public void addToWatchlist(String tconst) {
        String sql = """
                 INSERT INTO user_watchlist(tconst)
                 VALUES (?)
                 ON CONFLICT DO NOTHING;
                """;
        jdbcTemplate.update(sql, tconst);
    }

    @Override
    public void addToWatched(String tconst) {
        String sql = """
                 INSERT INTO user_watched(tconst)
                 VALUES (?)
                 ON CONFLICT DO NOTHING;
                """;
        jdbcTemplate.update(sql, tconst);

    }

    @Override
    public void addUserRating(String tconst, int rating) {
        String sql = """
                 INSERT INTO user_ratings(tconst, user_rating)
                 VALUES (?,?)
                 ON CONFLICT(tconst)
                 DO UPDATE SET user_rating = EXCLUDED.user_rating;
                """;
        jdbcTemplate.update(sql, tconst, rating);
    }

    @Override
    public void modifyUserRating(String tconst, int rating) {
        String sql = """
                 UPDATE user_ratings
                 SET user_rating = ?
                 WHERE tconst = ?;
                """;
        jdbcTemplate.update(sql, rating, tconst);
    }

    @Override
    public void removeUserRating(String tconst) {
        String sql = """
                 DELETE FROM user_ratings
                 WHERE tconst = ?;
                """;
        jdbcTemplate.update(sql, tconst);
    }

    @Override
    public void removeFromWatchlist(String tconst) {
        String sql = """
                 DELETE FROM user_watchlist
                 WHERE tconst = ?;
                """;
        jdbcTemplate.update(sql, tconst);
    }

    @Override
    public void removeFromWatched(String tconst) {
        String sql = """
                 DELETE FROM user_watched
                 WHERE tconst = ?;
                """;
        jdbcTemplate.update(sql, tconst);
    }

    @Override
    public Page<Watchlist> getWatchlist(Pageable pageable) {
        String rowCountSQL = """
                SELECT count(1) AS row_count FROM user_watchlist
                """;
        int rowCount = jdbcTemplate.queryForObject(rowCountSQL,(rs, rowNum) -> rs.getInt(1));
        String sql =
                "SELECT watchlist.tconst, b.primary_title, b.title_type, r.average_rating, ur.user_rating, watched.tconst AS watched " +
                "FROM user_watchlist watchlist " +
                "    LEFT JOIN title_basics b ON watchlist.tconst = b.tconst " +
                "    LEFT JOIN title_ratings r ON watchlist.tconst = r.tconst " +
                "    LEFT JOIN user_watched watched ON watchlist.tconst = watched.tconst " +
                "    LEFT JOIN user_ratings ur ON watchlist.tconst = ur.tconst "+
                "LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset() + ";";
        List<Watchlist> Results = jdbcTemplate.query(sql, new WatchlistRowMapper());
        return new PageImpl<>(Results, pageable, rowCount);
    }

    @Override
    public Page<Watched> getWatched(Pageable pageable) {
        String rowCountSQL = """
                SELECT count(1) AS row_count FROM user_watched
                """;
        int rowCount = jdbcTemplate.queryForObject(rowCountSQL,(rs, rowNum) -> rs.getInt(1));
        String sql =
                "SELECT watched.tconst, b.primary_title, b.title_type, r.average_rating, ur.user_rating, watchlist.tconst AS watchlist " +
                "FROM user_watched watched " +
                "    LEFT JOIN title_basics b ON watched.tconst = b.tconst " +
                "    LEFT JOIN title_ratings r ON watched.tconst = r.tconst " +
                "    LEFT JOIN user_watchlist watchlist ON watched.tconst = watchlist.tconst " +
                "    LEFT JOIN user_ratings ur ON watched.tconst = ur.tconst "+
                "LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset() + ";";

        List<Watched> Results = jdbcTemplate.query(sql, new WatchedRowMapper());
        return new PageImpl<>(Results, pageable, rowCount);
    }

    public Optional<Title> selectMovieByTconstV2(String tconst) {
        String sql = """
                SELECT b.tconst
                    , b.title_type
                    , b.primary_title
                    , b.original_title
                    , b.is_adult
                    , b.start_year
                    , b.end_year
                    , b.runtime_minutes
                    , b.genres
                    , array_agg(DISTINCT directors.director_name) AS directors
                    , array_agg(DISTINCT writers.writer_name)     AS writers
                    , r.average_rating
                    , r.num_votes
               FROM title_basics b
                        LEFT JOIN title_ratings r ON b.tconst = r.tconst
                        LEFT JOIN title_crew c on b.tconst = c.tconst
                        LEFT JOIN (SELECT tconst, n.primary_name AS director_name
                                   FROM name_basics n,
                                        (SELECT c.tconst tconst, unnest(c.directors) director_nconst
                                         FROM title_crew c,
                                              title_basics b
                                         WHERE c.tconst = b.tconst) as d
                                   WHERE d.director_nconst = n.nconst) as directors
                                  ON directors.tconst = b.tconst
                        LEFT JOIN (SELECT tconst, n.primary_name AS writer_name
                                   FROM name_basics n,
                                        (SELECT c.tconst tconst, unnest(c.writers) writer_nconst
                                         FROM title_crew c,
                                              title_basics b
                                         WHERE c.tconst = b.tconst) as d
                                   WHERE d.writer_nconst = n.nconst) as writers
                                  ON writers.tconst = b.tconst
               WHERE b.tconst = ?
               group by b.tconst, r.average_rating, r.num_votes;
                """;
        return jdbcTemplate.query(sql, new TitleRowMapper(), tconst).stream().findFirst();
    }
}
