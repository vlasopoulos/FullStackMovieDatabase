package io.github.vlasopoulos.FullStackMovieDatabase.api.rowMappers;

import io.github.vlasopoulos.FullStackMovieDatabase.api.records.Title;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TitleRowMapper implements RowMapper<Title> {

    @Override
    public Title mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Title(
                rs.getString("tconst"),
                rs.getString("title_type"),
                rs.getString("primary_title"),
                rs.getString("original_title"),
                intToBoolean(rs.getInt("is_adult")),
                rs.getInt("start_year"),
                rs.getInt("end_year"),
                rs.getInt("runtime_minutes"),
                List.of((String[])rs.getArray("genres").getArray()),
                List.of((String[])rs.getArray("directors").getArray()),
                List.of((String[])rs.getArray("writers").getArray()),
                rs.getFloat("average_rating"),
                rs.getInt("num_votes"),
                rs.getInt("user_rating"),
                rs.getString("watched") != null,
                rs.getString("watchlist") != null
        );
    }

    private boolean intToBoolean(int n) {
        return (n==1);
    }
}
