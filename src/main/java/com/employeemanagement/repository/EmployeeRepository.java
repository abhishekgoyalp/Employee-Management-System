package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.model.Employee;

/**
 * The {@code EmployeeRepository} interface extends Spring Data JPA repository
 * that provides CRUD operations for the {@code Employee} entity and its subclasses.
 *
 * <p>It enables automatic implementation of common database operations without
 * writing boilerplate code. Custom query methods can also be added if needed.
 *
 * Methods used by us:
 * - findAll(): Retrieves all employees, including their specific types.
 * - findById(Long id): Retrieves an employee by its unique identifier.
 * - save(Employee employee): Saves or updates an employee entity.
 * - deleteById(Long id): Deletes an employee by its ID.
 */

public interface EmployeeRepository<T extends Employee> extends JpaRepository<T, Long>{

}
