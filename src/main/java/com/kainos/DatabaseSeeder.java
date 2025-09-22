package com.kainos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner seedDatabase(EmployeeRepository employeeRepository, UserRepository userRepository) {
        return args -> {
            Random rand = new Random();
            String[] firstNames = {"Alex", "Stephen", "Jacob", "Mary", "Bob", "Alice", "Karen", "Samuel", "Lee", "Dylan"};
            String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};
            String[] jobRoles = {"Software Engineer", "Project Manager", "Data Analyst", "Product Owner", "UX Designer", "QA Tester", "DevOps Engineer", "Business Analyst"};
            Band[] enumValues = Band.values();

            final int NUM_TO_CREATE = 25;
            final int MIN_SALARY_FACTOR = 19;
            final int MAX_SALARY_FACTOR = 450;

            for (var employee : employeeRepository.findAll()) {
                employeeRepository.delete(employee);
            }

            for (var i = 0; i < NUM_TO_CREATE; i++) {
                String firstName = firstNames[rand.nextInt(0, firstNames.length - 1)];
                String lastName = lastNames[rand.nextInt(0, lastNames.length - 1)];
                Band band = enumValues[rand.nextInt(0, enumValues.length - 1)];
                String jobRole = jobRoles[rand.nextInt(0, jobRoles.length - 1)];
                int salary = rand.nextInt(MIN_SALARY_FACTOR, MAX_SALARY_FACTOR) * 1000;

                employeeRepository.save(new Employee(firstName, lastName, band, jobRole, salary));
            }

            userRepository.save(new User("alexmcc", "password"));
        };
    }
}
