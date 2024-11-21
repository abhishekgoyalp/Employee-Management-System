package com.employeemanagement.service;

import com.employeemanagement.model.ContractEmployee;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.PermanentEmployee;
import com.employeemanagement.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository<Employee> employeeRepository;

    @InjectMocks
    private EmployeeService<Employee> employeeService;

    private Employee permanentEmployee;
    private Employee contractEmployee;

    @BeforeEach
    void setUp() {
        permanentEmployee = new PermanentEmployee(5000.0, 10);
        permanentEmployee.setId(1L);
        permanentEmployee.setName("Test Name");
        permanentEmployee.setEmail("test.email@gmail.com");
        permanentEmployee.setSalary(500000.00);
        contractEmployee = new ContractEmployee(12, "ABC Agency");
        contractEmployee.setId(2L);
        contractEmployee.setName("Test Name 2");
        contractEmployee.setEmail("test.email2@gmail.com");
        contractEmployee.setSalary(200000.00);
    }

    @Test
    void createEmployee_Success() {
        when(employeeRepository.save(permanentEmployee)).thenReturn(permanentEmployee);

        Employee savedEmployee = employeeService.createEmployee(permanentEmployee);

        assertNotNull(savedEmployee);
        assertEquals("Test Name", savedEmployee.getName());
        assertEquals("test.email@gmail.com", savedEmployee.getEmail());
        verify(employeeRepository, times(1)).save(permanentEmployee);
    }

    @Test
    void getEmployeeById_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(permanentEmployee));

        Optional<Employee> retrievedEmployee = employeeService.getEmployeeById(1L);

        assertTrue(retrievedEmployee.isPresent());
        assertEquals("Test Name", retrievedEmployee.get().getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_Failure() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Employee> retrievedEmployee = employeeService.getEmployeeById(99L);

        assertFalse(retrievedEmployee.isPresent());
        verify(employeeRepository, times(1)).findById(99L);
    }

    @Test
    void getAllEmployees_Success() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(permanentEmployee, contractEmployee));

        List<Employee> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("Test Name", employees.get(0).getName());
        assertEquals("Test Name 2", employees.get(1).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void updateEmployee_Success() {
        PermanentEmployee updatedEmployee = new PermanentEmployee(5500.0, 9);
        updatedEmployee.setId(1L);
        updatedEmployee.setName("Test Name");
        updatedEmployee.setEmail("test.email@gmail.com");
        updatedEmployee.setSalary(500000.00);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(permanentEmployee));
        when(employeeRepository.save(any(PermanentEmployee.class))).thenReturn(updatedEmployee);

        Employee updated = employeeService.updateEmployee(1L, updatedEmployee);

        assertNotNull(updated);
        assertEquals(500000.0, updated.getSalary());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(PermanentEmployee.class));
    }

    @Test
    void updateEmployee_Failure() {
        PermanentEmployee updatedEmployee = new PermanentEmployee(5500.0, 9);
        updatedEmployee.setId(1L);
        updatedEmployee.setName("Test Name");
        updatedEmployee.setEmail("test.email@gmail.com");
        updatedEmployee.setSalary(500000.00);
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(99L, updatedEmployee);
        });

        assertEquals("Employee not found", exception.getMessage());
        verify(employeeRepository, times(1)).findById(99L);
    }

    @Test
    void deleteEmployee_Success() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEmployee_Failure() {
        doThrow(new RuntimeException("Employee not found")).when(employeeRepository).deleteById(99L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(99L);
        });

        assertEquals("Employee not found", exception.getMessage());
        verify(employeeRepository, times(1)).deleteById(99L);
    }
}
