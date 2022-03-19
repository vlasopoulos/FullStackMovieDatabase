package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PrincipalsRowMapper implements RowMapper<Principal> {

    @Override
    public Principal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Principal(
                rs.getString("tconst"),
                rs.getInt("ordering"),
                rs.getString("nconst"),
                rs.getString("category"),
                rs.getString("job"),
                List.of((String[])rs.getArray("characters").getArray())
        );
    }
}
