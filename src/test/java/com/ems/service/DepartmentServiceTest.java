package com.ems.service;

import com.ems.dto.CreateDepartmentRequest;
import com.ems.entity.Department;
import com.ems.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDepartment(){
        CreateDepartmentRequest department = new CreateDepartmentRequest();
        department.setName("Dev");

        when(departmentRepository.save(any(Department.class)))
                .thenAnswer(invocationOnMock -> {
                    Department dept = invocationOnMock.getArgument(0);
                    dept.setId(1L);
                    return dept;
                });

        Department result = departmentService.createDepartment(department);

        assertNotNull(result.getId());
        assertEquals("Dev" , result.getName());

    }

    @Test
    void testGetAllDepartment(){
        Department department1 = new Department();
        department1.setName("Developer");
        Department department2 = new Department();
        department2.setName("Tester");

        List<Department> departmentList = List.of(department1 , department2);

        when(departmentRepository.findAll()).thenReturn(departmentList);

        List<Department> result = departmentService.getAllDepartment();

        assertEquals(departmentList , result);
    }

}
