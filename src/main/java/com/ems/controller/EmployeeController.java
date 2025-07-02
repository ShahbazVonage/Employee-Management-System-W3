package com.ems.controller;

import com.ems.dto.EmployeeDto;
import com.ems.dto.EmployeeUpdateDto;
import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    /**
     * Method to create employee
     * @param employeeDto request body of the employee details
     * @return newly added employee
     */
    @PostMapping
    private Employee createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return service.createEmployee(employeeDto);
    }

    /**
     * Method to update employee details
     * @param id Id of employee whom the details will update
     * @param employeeDto request body of new updated data
     * @return update employee object
     */
    @PutMapping("/{id}")
    private Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateDto employeeDto){
        return service.updateEmployee(id , employeeDto);
    }

    /**
     * Method to return all the employees
     * @return list of employees
     */
    @GetMapping
    private List<Employee> getAllEmployee(){
        return service.getAllEmployees();
    }

    /**
     * Method to get employees by department name
     * @param name of the department
     * @return list of employees corresponding to name
     */
    @GetMapping("/department-name")
    public List<Employee> getByDepartmentName(@RequestParam String name) {
        return service.getEmployeesByDepartmentName(name);
    }

    /**
     * Method to get employees by managerId
     * @param managerId of the manager you want the employees
     * @return list of employees under that manager id
     */
    @GetMapping("/{managerId}")
    public List<Employee> getEmployeesByManagerId(@PathVariable Long managerId){
        return service.getEmployeesByManagerId(managerId);
    }

    /**
     * Method to set the status of employee isActive to false , no longer in company
     * @param id of the employee
     * @return string message of successfully delete
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteEmployee(id);
    }
}
