package io.github.vlasopoulos.FullStackMovieDatabase.api;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
          rs.getString("nconst"),
          rs.getString("primary_name"),
          rs.getInt("birth_year"),
          rs.getInt("death_year"),
          List.of((String[])rs.getArray("primary_profession").getArray()),
          List.of((String[])rs.getArray("known_for_titles").getArray())
        );
    }
}
