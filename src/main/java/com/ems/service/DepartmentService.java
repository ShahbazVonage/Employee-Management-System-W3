package com.ems.service;

import com.ems.dto.CreateDepartmentRequest;
import com.ems.entity.Department;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department createDepartment(CreateDepartmentRequest request){

        try{
            Department department = new Department();
            department.setName(request.getName());
            return  departmentRepository.save(department);
        } catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    public List<Department> getAllDepartment(){
        try{
            return departmentRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    public Department getDepartmentById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }
}
