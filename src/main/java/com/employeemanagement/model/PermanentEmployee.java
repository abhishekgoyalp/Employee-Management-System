package com.employeemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permanent_employees")
public class PermanentEmployee extends Employee {
    private Double bonus;

    private Integer leaveBalance;
}
