package com.ems.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@RequiredArgsConstructor
public class EmployeeUpdateDto {
    private String name;

    private int age;

    private BigDecimal salary;

    private String email;

    private Long managerId;

    private Long departmentId;
}
