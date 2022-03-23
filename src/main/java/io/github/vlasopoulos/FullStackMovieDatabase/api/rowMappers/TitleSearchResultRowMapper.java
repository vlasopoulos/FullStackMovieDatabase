package io.github.vlasopoulos.FullStackMovieDatabase.api.rowMappers;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.TitleSearchResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TitleSearchResultRowMapper implements RowMapper<TitleSearchResult> {
    @Override
    public TitleSearchResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TitleSearchResult(
                rs.getString("tconst"),
                rs.getString("title_type"),
                rs.getString("primary_title"),
                intToBoolean(rs.getInt("is_adult")),
                rs.getInt("start_year"),
                rs.getInt("end_year"),
                List.of((String[])rs.getArray("genres").getArray()),
                rs.getFloat("average_rating"),
                rs.getInt("user_rating"),
                rs.getString("watched") != null,
                rs.getString("watchlist") != null
                );
    }

    private boolean intToBoolean(int n) {
        return (n==1);
    }
}
