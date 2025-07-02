package com.ems.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateDepartmentRequest {
    @NotBlank(message = "Department name is required")
    String name;
}
