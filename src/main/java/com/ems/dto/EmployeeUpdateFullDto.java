package com.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@RequiredArgsConstructor
public class EmployeeUpdateFullDto {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 18 , message = "Age must be 18 or above")
    private int age;

    @Min(value = 15000 , message = "Vonage minimum wage must be 15000 or above")
    private BigDecimal salary;

    @Email @NotNull @NotBlank
    private String email;

    @NotNull
    private Long managerId;

    @NotNull
    private Long departmentId;
}
