package com.employeemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@code ContractEmployee} class represents employees hired on a contractual basis.
 * It extends the {@code Employee} class and includes specific attributes like contractDurationInMonths.
 *
 * <p>Attributes:
 * - contractDurationInMonths: The duration of the contract of employee in months.
 * - agencyName: Agency name which provide this employee on contract basis
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contract_employees")
public class ContractEmployee extends Employee {

    private Integer contractDurationInMonths;
    private String agencyName;
}
