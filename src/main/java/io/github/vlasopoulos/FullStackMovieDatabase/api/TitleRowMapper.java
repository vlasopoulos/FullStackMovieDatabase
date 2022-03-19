package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
                rs.getInt("num_votes")
        );
    }

    public Title mapRowV2(ResultSet rs, int rowNum) throws SQLException {
        List<String> genres,directors,writers;
        if (rs.getArray("genres").toString().equals("{NULL}")) genres = List.of();
        else genres = List.of((String[])rs.getArray("genres").getArray());
        if (rs.getArray("directors").toString().equals("{NULL}")) directors = List.of();
        else directors = List.of((String[])rs.getArray("directors").getArray());
        if (rs.getArray("writers").toString().equals("{NULL}")) writers = List.of();
        else writers = List.of((String[])rs.getArray("writers").getArray());
        return new Title(
                rs.getString("tconst"),
                rs.getString("title_type"),
                rs.getString("primary_title"),
                rs.getString("original_title"),
                intToBoolean(rs.getInt("is_adult")),
                rs.getInt("start_year"),
                rs.getInt("end_year"),
                rs.getInt("runtime_minutes"),
                genres,
                directors,
                writers,
                rs.getFloat("average_rating"),
                rs.getInt("num_votes")
        );
    }

    private boolean intToBoolean(int n) {
        return (n==1);
    }
}
