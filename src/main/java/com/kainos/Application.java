package com.kainos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner seedDatabase(EmployeeRepository repository) {
        return args -> {
            repository.save(new Employee("Alice", "Smith", Band.ASSOCIATE, "Developer", 50000));
            repository.save(new Employee("Bob", "Johnson", Band.MANAGER, "Designer", 45000));
        };
    }
}