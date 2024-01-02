package ru.myPackage.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Builder")
public class Builder extends Employee implements Comparable<Builder> {

    public Builder() {
    }

    public Builder(
            int Id,
            String idOfEmployee,
            String surname,
            String name,
            String lastName,
            String email,
            double salary,
            int timeOfWork,
            State state) {
        super(Id, idOfEmployee, surname, name, lastName, email, salary, timeOfWork, state);
    }

    public double currentSalary() {
        return getSalary() * getTimeOfWork();
    }


    @Override
    public int compareTo(Builder o) {
//        String index_1 = getIdOfEmployee().substring(1);
//        String index_2 = o.getIdOfEmployee().substring(1);
//        int i_1 = Integer.parseInt(index_1);
//        int i_2 = Integer.parseInt(index_2);
//        return i_1 - i_2;
        return getId() - o.getId();
    }
}
