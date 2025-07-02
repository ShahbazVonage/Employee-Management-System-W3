package com.ems.controller;

import com.ems.dto.CreateDepartmentRequest;
import com.ems.entity.Department;
import com.ems.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;

    @PostMapping
    public Department createDepartment(@Valid @RequestBody CreateDepartmentRequest request){
        return service.createDepartment(request);
    }
    @GetMapping
    public List<Department> getAllDepartment(){
        return service.getAllDepartment();
    }
}
