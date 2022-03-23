package com.tarigor.javamastery.service;

import com.tarigor.javamastery.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    Employee updateEmployeeData(Long id, Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> findByFirstOrAndLastName(Map<String, String> employeeDetailsMap);
}
