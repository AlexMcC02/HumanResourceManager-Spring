package com.kainos;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "band", nullable = false)
    private Band band;
    @Column(name = "job_role", nullable = false)
    private String jobRole;
    @Column(name = "salary", nullable = false)
    private int salary;

    public Employee() {}

    public Employee(String firstName, String lastName, Band band, String jobRole, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.band = band;
        this.jobRole = jobRole;
        this.salary = salary;
    }

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
        Employee employee = (Employee) o;
        return salary == employee.salary && Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && band == employee.band && Objects.equals(jobRole, employee.jobRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, jobRole, salary);
    }
}
