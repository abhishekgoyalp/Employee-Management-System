package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.model.Employee;

public interface EmployeeRepository<T extends Employee> extends JpaRepository<T, Long>{

}
