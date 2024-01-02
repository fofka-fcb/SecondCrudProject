package ru.myPackage.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.myPackage.models.Builder;
import ru.myPackage.models.State;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuilderMapper implements RowMapper<Builder> {

    @Override
    public Builder mapRow(ResultSet rs, int rowNum) throws SQLException {

        Builder builder = new Builder();

        builder.setId(rs.getInt("id"));
        builder.setIdOfEmployee(rs.getString("id_of_employee"));
        builder.setSurname(rs.getString("surname"));
        builder.setName(rs.getString("name"));
        builder.setLastName(rs.getString("last_name"));
        builder.setEmail(rs.getString("email"));
        builder.setSalary(rs.getDouble("salary"));
        builder.setTimeOfWork(rs.getInt("time_of_work"));
        builder.setState(State.valueOf((rs.getString("sick_leave"))));

        return builder;
    }
}
