package com.employeemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@code PermanentEmployee} class represents employees who are on permanent payroll.
 * It extends the {@code Employee} class and adds specific attributes such as bonus and leaveBalance.
 *
 * <p>Attributes:
 * - bonus: The bonus amount assigned to the permanent employee.
 * - leaveBalance: The Leave Balance quota available
 */

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
