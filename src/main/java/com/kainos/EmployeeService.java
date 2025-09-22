package com.kainos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with supplied ID!"));
    }

    public void createEmployee(EmployeeDTO employeeDTO) {
        var employeeToCreate = new Employee(
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getBand(),
                employeeDTO.getJobRole(),
                employeeDTO.getSalary()
        );

        employeeRepository.save(employeeToCreate);
    }

    public void updateEmployee(Long id, EmployeeDTO updatedEmployee) {
        employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setBand(updatedEmployee.getBand());
            employee.setJobRole(updatedEmployee.getJobRole());
            employee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
