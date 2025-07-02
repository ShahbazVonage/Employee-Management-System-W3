package com.ems.repository;

import com.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {

    List<Employee> findAllByIsActiveTrue();
    Optional<Employee> findByIdAndIsActiveTrue(Long id);
    List<Employee> findByManagerIdAndIsActiveTrue(Long id);
    List<Employee> findByDepartmentIdAndIsActiveTrue(Long id);
    List<Employee> findByDepartmentNameAndIsActiveTrue(String departmentName);
}
