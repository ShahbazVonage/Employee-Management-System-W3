package com.ems.service;


import com.ems.dto.EmployeeDto;
import com.ems.dto.EmployeeUpdateFullDto;
import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.repository.DepartmentRepository;
import com.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployeeSuccessfully(){
        Department department = new Department();
        department.setId(1L);
        department.setName("Dev");

        EmployeeDto request = new EmployeeDto();
        request.setName("Shahbaz");
        request.setAge(21);
        request.setSalary(BigDecimal.valueOf(50000));
        request.setEmail("shahbaz123@gmail.com");
        request.setDepartmentId(1L);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.createEmployee(request);

        assertEquals("Shahbaz" , result.getName());
        assertEquals(21 , result.getAge());
        assertEquals(BigDecimal.valueOf(50000) , result.getSalary());

        assertTrue(result.getIsActive());
    }

    @Test
    void testUpdateEmployee(){
        Long empId = 1L;
        Long departId = 2L;

        Employee existing = new Employee();
        existing.setId(empId);
        existing.setName("Shabaz");
        existing.setIsActive(true);

        Department department = new Department();
        department.setId(departId);

        EmployeeUpdateFullDto request = new EmployeeUpdateFullDto();
        request.setDepartmentId(departId);
        request.setName("Shahbaz Khan");

        when(departmentRepository.findById(departId)).thenReturn(Optional.of(department));
        when(employeeRepository.findByIdAndIsActiveTrue(empId)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Employee employee = employeeService.updateEmployee(empId , request);

        assertEquals("Shahbaz Khan" , existing.getName());
    }

    @Test
   void testGetAllEmployee(){
        Employee employee1 = new Employee();
        employee1.setName("Shahbaz");
        employee1.setAge(21);
        employee1.setEmail("Shahbaz123@gmail.com");
        employee1.setSalary(BigDecimal.valueOf(50000));
        employee1.setIsActive(true);

        Employee employee2 = new Employee();
        employee2.setName("Shahbaz");
        employee2.setAge(21);
        employee2.setEmail("Shahbaz123@gmail.com");
        employee2.setSalary(BigDecimal.valueOf(50000));
        employee2.setIsActive(true);

        List<Employee> employeeList = List.of(employee1 , employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> resultList = employeeService.getAllEmployees();

        assertEquals(employeeList , resultList);
   }

   @Test
   void testGetEmployeesByDepartmentName(){
        String departmentName = "DEV";
        Long departId = 1L;

        Department department = new Department();
        department.setName(departmentName);
        department.setId(departId);

        Employee employee1 = new Employee();
        employee1.setName("Shahbaz");
        employee1.setAge(21);
        employee1.setEmail("Shahbaz123@gmail.com");
        employee1.setDepartment(department);

       Employee employee2 = new Employee();
       employee2.setName("Anna");
       employee2.setAge(21);
       employee2.setEmail("Anna123@gmail.com");
       employee2.setDepartment(department);

       List<Employee> employeeList = List.of(employee1 , employee2);

       when(employeeRepository.findByDepartmentNameAndIsActiveTrue(departmentName)).thenReturn(employeeList);

       List<Employee> resultList = employeeService.getEmployeesByDepartmentName(departmentName);

       assertEquals(employeeList , resultList);
   }

   @Test
   void testGetEmployeesByManagerId(){

        Long managerId = 10L;
       Department department = new Department();
       department.setId(1L);

       Employee manager = new Employee();
       manager.setId(managerId);
       manager.setName("Shwetha");
       manager.setAge(29);
       manager.setEmail("Shwetha123@gmail.com");
       manager.setDepartment(department);

       Employee employee1 = new Employee();
       employee1.setName("Shahbaz");
       employee1.setAge(21);
       employee1.setEmail("Shahbaz123@gmail.com");
       employee1.setDepartment(department);
       employee1.setManager(manager);

       Employee employee2 = new Employee();
       employee2.setName("Anna");
       employee2.setAge(21);
       employee2.setEmail("Anna123@gmail.com");
       employee2.setDepartment(department);
       employee2.setManager(manager);

       List<Employee> employeeList = List.of(employee1 , employee2);

       when(employeeRepository.findByManagerIdAndIsActiveTrue(managerId)).thenReturn(employeeList);

       List<Employee> resultList = employeeService.getEmployeesByManagerId(managerId);

       assertEquals(employeeList , resultList);
   }

   @Test
   void testDeleteEmployee(){
        Long empId = 1L;

        Department department = new Department();
        department.setId(2L);

        Employee employee = new Employee();
        employee.setId(empId);
        employee.setName("Shahbaz");
        employee.setAge(21);
        employee.setEmail("Shahbaz123@gmail.com");
        employee.setDepartment(department);
        employee.setIsActive(true);

        when(employeeRepository.findByIdAndIsActiveTrue(empId)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocationOnMock ->  invocationOnMock.getArgument(0));

        String result = employeeService.deleteEmployee(empId);
        assertEquals("Employee is Deleted Successfully" , result);
   }
}
