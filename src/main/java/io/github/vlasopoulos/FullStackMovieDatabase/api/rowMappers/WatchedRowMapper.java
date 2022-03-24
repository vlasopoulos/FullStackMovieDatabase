package io.github.vlasopoulos.FullStackMovieDatabase.api.rowMappers;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Watched;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchedRowMapper implements RowMapper<Watched> {
    @Override
    public Watched mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Watched(
            rs.getString("tconst"),
            rs.getString("primary_title"),
            rs.getString("title_type"),
            rs.getFloat("average_rating"),
            rs.getInt("user_rating"),
            rs.getString("watchlist") != null
        );
    }
}
