package ru.myPackage.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Employee {

//    @NotEmpty(message = "ID of employee should not be empty")
    private String idOfEmployee;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Lust name should not be empty")
    @Size(min = 2, max = 30, message = "Lust name should be between 2 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 10, message = "Age should be greater than 10.0")
    private double salary;

    private int timeOfWork;

    private State state;

}

