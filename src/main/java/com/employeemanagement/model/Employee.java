package com.employeemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * The {@code Employee} class serves as the base entity for all types of employees.
 * It contains common attributes shared by all employee types.
 *
 * <p>This class is abstract and cannot be instantiated directly. It is extended
 * by {@code PermanentEmployee} and {@code ContractEmployee}.
 *
 * Attributes:
 * - id: Unique identifier for the employee.
 * - name: Name of the employee.
 * - email: Email address of the employee.
 * - salary: Salary of the employee.
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,      // Include type information as a property
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"               // The JSON property used to identify the type
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PermanentEmployee.class, name = "permanent"),
    @JsonSubTypes.Type(value = ContractEmployee.class, name = "contract")
})
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "employees")
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Double salary;
}

