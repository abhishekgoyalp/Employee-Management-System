package com.employeemanagement.test;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.employeemanagement.model.Employee;
import com.employeemanagement.model.PermanentEmployee;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.EmployeeService;

class EmployeeServiceTest {
    @SuppressWarnings("unchecked")
	private final EmployeeRepository<Employee> employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeService<Employee> employeeService = new EmployeeService<>(employeeRepository);

    @Test
    void testCreateEmployee() {
        Employee employee = new PermanentEmployee();
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setSalary(50000.0);

        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertThat(result.getName()).isEqualTo("John Doe");
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById_Success() {
        Employee employee = new PermanentEmployee();
        employee.setId(1L);
        employee.setName("Jane Doe");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertThat(result.isPresent()).isFalse();
    }
}

