package io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch;

import io.github.vlasopoulos.FullStackMovieDatabase.api.MovieDatabaseService;
import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.sql.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.SQLException;

@Component
public class UpdateDatabaseTables {

    private final JdbcTemplate jdbcTemplate;

    public UpdateDatabaseTables(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void update() {
        try {
            PGConnection conn = jdbcTemplate.getDataSource().getConnection().unwrap(PGConnection.class);
            CopyManager cm = new CopyManager((BaseConnection) conn);
            BufferedReader br;

            String sql;
            //create temp tables
            sql = """
                       CREATE TABLE title_basics_temp (
                                    tconst text PRIMARY KEY,
                                    title_type text,
                                    primary_title text,
                                    original_title text,
                                    is_adult smallint,
                                    start_year int,
                                    end_year int,
                                    runtime_minutes int,
                                    genres text[]
                                    );

                        CREATE TABLE name_basics_temp (
                                      nconst text PRIMARY KEY,
                                      primary_name text,
                                      birth_year int,
                                      death_year int,
                                      primary_profession text[],
                                      known_for_titles text[]
                                      );

                        CREATE TABLE title_crew_temp (
                                      tconst text PRIMARY KEY,
                                      directors text[],
                                      writers text[]
                                      );

                        CREATE TABLE title_principals_temp (
                                    tconst text,
                                    ordering int,
                                    nconst text,
                                    category text,
                                    job text,
                                    characters text[],
                                    PRIMARY KEY (tconst, ordering)
                                    );

                         CREATE TABLE title_episode_temp (
                                 tconst text PRIMARY KEY,
                                 parent_tconst text,
                                 season_number int,
                                 episode_number int
                                 );

                         CREATE TABLE title_akas_temp (
                                 tconst text,
                                 ordering int,
                                 localized_title text,
                                 region text,
                                 language text,
                                 types text,
                                 attributes text,
                                 is_original_title smallint,
                                 PRIMARY KEY (tconst, ordering)
                                 );

                         CREATE TABLE title_ratings_temp (
                                 tconst text PRIMARY KEY,
                                 average_rating numeric(3,1),
                                 num_votes int
                         );

                    """;
            jdbcTemplate.execute(sql);

            //update title_basics table
            br = new BufferedReader(new FileReader(new File("temp/title.basics.output.tsv")));
            System.out.println("Copying title.basics.output.tsv into table title_basics_temp");
            sql = """
                      COPY title_basics_temp
                      FROM STDIN
                      DELIMITER E'\t'
                      CSV
                      HEADER
                      NULL '\\N';
                    """;
            System.out.println("Wrote " + cm.copyIn(sql, br) + " lines into table title_basics_temp");
            br.close();

            System.out.println("Updating table title_basics");
            sql = """
                    INSERT INTO title_basics
                    SELECT *
                    FROM title_basics_temp
                    ON CONFLICT (tconst)
                    DO UPDATE SET primary_title = EXCLUDED.primary_title, original_title = EXCLUDED.original_title;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Updated table title_basics");

            sql = """
                    DROP TABLE title_basics_temp;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Dropped table title_basics_temp");

            //update name_basics table
            br = new BufferedReader(new FileReader(new File("temp/name.basics.output.tsv")));
            System.out.println("Copying name.basics.output.tsv into table name_basics_temp");
            sql = """
                      COPY name_basics_temp
                      FROM STDIN
                      DELIMITER E'\t'
                      CSV
                      HEADER
                      NULL '\\N';
                    """;
            System.out.println("Wrote " + cm.copyIn(sql, br) + " lines into table name_basics_temp");
            br.close();

            System.out.println("Updating table name_basics");
            sql = """
                    INSERT INTO name_basics
                    SELECT *
                    FROM name_basics_temp
                    ON CONFLICT
                    DO NOTHING;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Updated table name_basics");

            sql = """
                    DROP TABLE name_basics_temp;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Dropped table name_basics_temp");

            //update title_crew table
            br = new BufferedReader(new FileReader(new File("temp/title.crew.output.tsv")));
            System.out.println("Copying title.crew.output.tsv into table title_crew_temp");
            sql = """
                      COPY title_crew_temp
                      FROM STDIN
                      DELIMITER E'\t'
                      CSV
                      HEADER
                      NULL '\\N';
                    """;
            System.out.println("Wrote " + cm.copyIn(sql, br) + " lines into table title_crew_temp");
            br.close();

            System.out.println("Updating table title_crew");
            sql = """
                    INSERT INTO title_crew
                    SELECT *
                    FROM title_crew_temp
                    WHERE title_crew_temp.tconst IN (SELECT tconst FROM title_basics)
                    ON CONFLICT
                    DO NOTHING;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Updated table title_crew");

            sql = """
                    DROP TABLE title_crew_temp;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Dropped table title_crew_temp");

            //update title_principals table
            br = new BufferedReader(new FileReader(new File("temp/title.principals.output.tsv")));
            System.out.println("Copying title.principals.output.tsv into table title_principals_temp");
            sql = """
                      COPY title_principals_temp
                      FROM STDIN
                      DELIMITER E'\t'
                      CSV
                      HEADER
                      NULL '\\N';
                    """;
            System.out.println("Wrote " + cm.copyIn(sql, br) + " lines into table title_principals_temp");
            br.close();

            System.out.println("Updating table title_principals");
            sql = """
                    INSERT INTO title_principals
                    SELECT *
                    FROM title_principals_temp
                    WHERE title_principals_temp.tconst IN (SELECT tconst FROM title_basics)
                    ON CONFLICT
                    DO NOTHING;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Updated table title_principals");

            sql = """
                    DROP TABLE title_principals_temp;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Dropped table title_principals_temp");

            //update title_episode table
            br = new BufferedReader(new FileReader(new File("temp/title.episode.output.tsv")));
            System.out.println("Copying title.episode.output.tsv into table title_episode_temp");
            sql = """
                      COPY title_episode_temp
                      FROM STDIN
                      DELIMITER E'\t'
                      CSV
                      HEADER
                      NULL '\\N';
                    """;
            System.out.println("Wrote " + cm.copyIn(sql, br) + " lines into table title_episode_temp");
            br.close();

            System.out.println("Updating table title_episode");
            sql = """
                    INSERT INTO title_episode
                    SELECT *
                    FROM title_episode_temp
                    WHERE title_episode_temp.parent_tconst IN (SELECT tconst FROM title_basics)
                    ON CONFLICT
                    DO NOTHING;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Updated table title_episode");

            sql = """
                    DROP TABLE title_episode_temp;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Dropped table title_episode_temp");

            //update title_akas table
            br = new BufferedReader(new FileReader(new File("temp/title.akas.output.tsv")));
            System.out.println("Copying title.akas.output.tsv into table title_akas_temp");
            sql = """
                      COPY title_akas_temp
                      FROM STDIN
                      DELIMITER E'\t'
                      CSV
                      HEADER
                      NULL '\\N';
                    """;
            System.out.println("Wrote " + cm.copyIn(sql, br) + " lines into table title_akas_temp");
            br.close();

            System.out.println("Updating table title_akas");
            sql = """
                    INSERT INTO title_akas
                    SELECT *
                    FROM title_akas_temp
                    WHERE title_akas_temp.tconst IN (SELECT tconst FROM title_basics)
                    ON CONFLICT
                    DO NOTHING;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Updated table title_akas");

            sql = """
                    DROP TABLE title_akas_temp;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Dropped table title_akas_temp");

            //update title_ratings table
            br = new BufferedReader(new FileReader(new File("temp/title.ratings.output.tsv")));
            System.out.println("Copying title.ratings.output.tsv into table title_ratings_temp");
            sql = """
                      COPY title_ratings_temp
                      FROM STDIN
                      DELIMITER E'\t'
                      CSV
                      HEADER
                      NULL '\\N';
                    """;
            System.out.println("Wrote " + cm.copyIn(sql, br) + " lines into table title_ratings_temp");
            br.close();

            System.out.println("Updating table title_ratings");
            sql = """
                    INSERT INTO title_ratings
                    SELECT *
                    FROM title_ratings_temp
                    WHERE title_ratings_temp.tconst IN (SELECT tconst FROM title_basics)
                    ON CONFLICT (tconst)
                    DO UPDATE SET average_rating = EXCLUDED.average_rating, num_votes = EXCLUDED.num_votes;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Updated table title_ratings");

            sql = """
                    DROP TABLE title_ratings_temp;
                """;
            jdbcTemplate.execute(sql);
            System.out.println("Dropped table title_ratings_temp");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
