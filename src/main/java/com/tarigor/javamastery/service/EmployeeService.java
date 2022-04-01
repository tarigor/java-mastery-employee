package com.tarigor.javamastery.service;

import com.tarigor.javamastery.dto.EmployeeDTO;
import com.tarigor.javamastery.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long employeeId);

    Employee updateEmployeeData(Long id, EmployeeDTO employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> findByFirstOrAndLastName(String firstName, String lastName);
}
