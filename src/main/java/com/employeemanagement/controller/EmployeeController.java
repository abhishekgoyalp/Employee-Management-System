package com.employeemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.employeemanagement.model.Employee;
import com.employeemanagement.service.EmployeeService;

/**
 * The {@code EmployeeController} class is a REST controller that handles API
 * requests for managing employees.
 *
 * <p>It maps incoming HTTP requests to specific service methods and returns
 * appropriate responses to the client.
 *
 * Endpoints:
 * - GET /api/employees: Retrieves all employees.
 * - GET /api/employees/{id}: Retrieves an employee by ID.
 * - POST /api/employees: Creates a new employee.
 * - PUT /api/employees/{id}: Updates an existing employee.
 * - DELETE /api/employees/{id}: Deletes an employee.
 *
 * <p>This class is annotated with {@code @RestController}, {@code @RequestMapping},
 * and {@code @CrossOrigin} to handle cross-origin requests.
 */

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService<Employee> employeeService;

    public EmployeeController(EmployeeService<Employee> employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
