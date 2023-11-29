package ru.myPackage.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.myPackage.models.Builder;
import ru.myPackage.models.State;

import java.util.List;

@Component
public class ModelsDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ModelsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Builder> index() {
//        return jdbcTemplate.query("SELECT * FROM Builder", new BeanPropertyRowMapper<>(Builder.class));
        return jdbcTemplate.query("SELECT * FROM Builder", new BuilderMapper()).stream().sorted().toList();
    }

    public Builder show(String idOfEmployee) {
//        return jdbcTemplate.query("SELECT * FROM Builder WHERE id_of_employee=?", new Object[]{idOfEmployee}, new BeanPropertyRowMapper<>(Builder.class))
//                .stream().findAny().orElse(null);
        return jdbcTemplate.query("SELECT * FROM Builder WHERE id_of_employee=?", new Object[]{idOfEmployee}, new BuilderMapper())
                .stream().findAny().orElse(null);
    }

    public Builder showByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM Builder WHERE email=?", new Object[]{email}, new BuilderMapper())
                .stream().findAny().orElse(null);
//        return jdbcTemplate.query("SELECT * FROM Builder WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(Builder.class))
//                .stream().findAny().orElse(null);
    }

    public void save(Builder builder) {
        jdbcTemplate.update("INSERT INTO Builder (id_of_employee, surname, name, last_name, email, salary, time_of_work, sick_leave) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                lustId(),
                builder.getSurname(),
                builder.getName(),
                builder.getLastName(),
                builder.getEmail(),
                builder.getSalary(),
                builder.getTimeOfWork(),
                State.NONE.toString());
    }

    public void update(String idOfEmployee, Builder updateBuilder) {
        jdbcTemplate.update("UPDATE Builder SET surname=?, name=?, last_name=?, email=?, salary=?, time_of_work=?, sick_leave=? WHERE id_of_employee=?",
                updateBuilder.getSurname(),
                updateBuilder.getName(),
                updateBuilder.getLastName(),
                updateBuilder.getEmail(),
                updateBuilder.getSalary(),
                updateBuilder.getTimeOfWork(),
                updateBuilder.getState().toString(),
                idOfEmployee);
    }

    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM Builder WHERE id_of_employee=?", id);
    }

    public void updateTime(String idOfEmployee, int time) {
        Builder nowBuilder = show(idOfEmployee);
        nowBuilder.setTimeOfWork(nowBuilder.getTimeOfWork() + time);

        jdbcTemplate.update("UPDATE Builder SET time_of_work=? WHERE id_of_employee=?",
                nowBuilder.getTimeOfWork(),
                nowBuilder.getIdOfEmployee());
    }

    public String lustId() {
        Builder builder = jdbcTemplate.query("SELECT * FROM Builder ORDER BY id_of_employee DESC LIMIT 1", new BuilderMapper())
                .stream().findAny().orElse(null);
        if (builder != null) {
            String id = builder.getIdOfEmployee();
            String resInt = String.valueOf(Integer.parseInt(id.substring(1)) + 1);
            for (;;) {
                if (resInt.length() < 3) {
                    resInt = "0" + resInt;
                } else break;
            }
            resInt = id.charAt(0) + resInt;

            return resInt;
        } else {
            return "B001";
        }
    }

}
