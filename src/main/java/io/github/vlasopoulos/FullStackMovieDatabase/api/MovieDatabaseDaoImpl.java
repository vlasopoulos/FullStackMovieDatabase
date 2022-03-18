package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
