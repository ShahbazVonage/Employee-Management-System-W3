package com.ems.service;

import com.ems.dto.EmployeeDto;
import com.ems.dto.EmployeeUpdateDto;
import com.ems.dto.EmployeeUpdateFullDto;
import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.DepartmentRepository;
import com.ems.repository.EmployeeRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * Create/Add employee to the database
     * @param request en employee dto request
     * @return Employee object of newly created employee
     */
    public Employee createEmployee(EmployeeDto request){
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Employee manager = null;
        if(request.getManagerId() != null){
            manager = employeeRepository.findByIdAndIsActiveTrue(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        }
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setAge(request.getAge());
        employee.setSalary(request.getSalary());
        employee.setDepartment(department);
        employee.setIsActive(true);
        try{
            return employeeRepository.save(employee);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Email should be unique");
        } catch (Exception ex){
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }

    /**
     * Method to update the employee data
     * @param id of the employee whom the updation take place
     * @param request body of the newly updated employee
     * @return new employee object
     */
    public Employee updateEmployee(Long id , EmployeeUpdateFullDto request){
        Employee employee = getEmployeeById(id);
        Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Employee manager = null;
        if(request.getManagerId() != null){
            manager = employeeRepository.findByIdAndIsActiveTrue(request.getManagerId()).orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        }
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setManager(manager);
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    /**
     * Method to update the employee data partially
     * @param id of the employee whom the updation take place
     * @param request body of the newly updated employee
     * @return new employee object
     */
    public Employee partialUpdateEmployee(Long id  , EmployeeUpdateDto request){
        Employee employee = getEmployeeById(id);
        Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Employee manager = null;
        if(request.getManagerId() != null){
            manager = employeeRepository.findByIdAndIsActiveTrue(request.getManagerId()).orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        }
        if(request.getName() != null) employee.setName(request.getName());
        if(request.getAge() != 0) employee.setAge(request.getAge());
        if(request.getEmail() != null) employee.setEmail(request.getEmail());
        if(request.getSalary() != null) employee.setSalary(request.getSalary());
        if(manager !=null)employee.setManager(manager);
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    // get all the employee by department name
    public List<Employee> getEmployeesByDepartmentName(String departmentName){
        return employeeRepository.findByDepartmentNameAndIsActiveTrue(departmentName);
    }
    // get the employee by manager Id
    public List<Employee> getEmployeesByManagerId(Long managerId){
        return employeeRepository.findByManagerIdAndIsActiveTrue(managerId);
    }
    // delete the employee by id
    public String deleteEmployee(Long id){
        Employee employee = getEmployeeById(id);
        employee.setIsActive(false);
        try {
            employeeRepository.save(employee);
            return "Employee is Deleted Successfully";
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Email should be unique");
        }catch (Exception ex){
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }
    // get employee by id
    public Employee getEmployeeById(Long id){
        return employeeRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }


}
