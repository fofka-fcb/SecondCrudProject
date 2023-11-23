package ru.myPackage.models;

import ru.myPackage.interfaceOfModels.CountTimeOfWork;
import ru.myPackage.interfaceOfModels.SalaryCalculation;


public class Builder extends Employee implements SalaryCalculation, CountTimeOfWork {

    public Builder() {
    }

    public Builder(String idOfEmployer, String surname, String name, String lustName, String email, double salary, int timeOfWork) {
        super(idOfEmployer, surname, name, lustName, email, salary, timeOfWork);
    }

    @Override
    public int salaryCalculate(int time, int salaryInHour) {
        return time * salaryInHour;
    }

    @Override
    public void countTime(int time) {
        this.setTimeOfWork(getTimeOfWork() + time);
    }
}
