package com.kainos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class EmployeeDTO {
    @NotBlank(message = "First Name is a required field.")
    private String firstName;
    @NotBlank(message = "Second Name is a required field.")
    private String lastName;
    private Band band;
    @NotBlank(message = "Job Role is a required field.")
    private String jobRole;
    @Min(value = 19000, message = "The minimum salary is 19,000.")
    private int salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return salary == that.salary && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && band == that.band && Objects.equals(jobRole, that.jobRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, band, jobRole, salary);
    }
}
