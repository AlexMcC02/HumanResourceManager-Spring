package com.kainos;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public PageResponse<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Employee> employeePage = employeeService.getAllEmployees(page, size);

        PageResponse<Employee> response = new PageResponse<>();
        response.setContent(employeePage.getContent());
        response.setPageNumber(employeePage.getNumber());
        response.setPageSize(employeePage.getSize());
        response.setTotalElements(employeePage.getTotalElements());
        response.setTotalPages(employeePage.getTotalPages());
        response.setLast(employeePage.isLast());

        return response;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/create_employee")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit_employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO updatedEmployee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete_employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
