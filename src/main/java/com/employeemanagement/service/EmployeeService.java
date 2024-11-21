package com.employeemanagement.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.employeemanagement.model.ContractEmployee;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.PermanentEmployee;
import com.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService<T extends Employee> {
    private final EmployeeRepository<T> employeeRepository;

    public EmployeeService(EmployeeRepository<T> employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public T createEmployee(T employee) {
        return employeeRepository.save(employee);
    }

    public Optional<T> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<T> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public T updateEmployee(Long id, T updatedEmployee) {
        T existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Update common fields
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        // Check type and update subclass-specific fields
        if (existingEmployee instanceof PermanentEmployee && updatedEmployee instanceof PermanentEmployee) {
            PermanentEmployee existingPerm = (PermanentEmployee) existingEmployee;
            PermanentEmployee updatedPerm = (PermanentEmployee) updatedEmployee;

            existingPerm.setBonus(updatedPerm.getBonus());
            existingPerm.setLeaveBalance(updatedPerm.getLeaveBalance());
        } else if (existingEmployee instanceof ContractEmployee && updatedEmployee instanceof ContractEmployee) {
            ContractEmployee existingContract = (ContractEmployee) existingEmployee;
            ContractEmployee updatedContract = (ContractEmployee) updatedEmployee;

            existingContract.setContractDurationInMonths(updatedContract.getContractDurationInMonths());
            existingContract.setAgencyName(updatedContract.getAgencyName());
        }

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

