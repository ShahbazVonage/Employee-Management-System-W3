package com.ems.dto;

import com.ems.entity.Department;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class EmployeeDto {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Min(value = 18 , message = "Age must be 18 or above")
    private int age;

    @Min(value = 15000 , message = "Vonage minimum wage must be 15000 or above")
    private BigDecimal salary;

    @Column(unique = true)
    @Email @NotNull @NotBlank
    private String email;

    private Long managerId;

    @NotNull
    private Long departmentId;
}
