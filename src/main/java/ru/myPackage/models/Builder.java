package ru.myPackage.models;

public class Builder extends Employee{

    public Builder() {
    }

    public Builder(String idOfEmployer, String surname, String name, String lustName, String email, double salary, int timeOfWork, State state) {
        super(idOfEmployer, surname, name, lustName, email, salary, timeOfWork, state);

    }

    public double currentSalary() {
        return getSalary() * getTimeOfWork();
    }


}
