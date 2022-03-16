package com.tarigor.javamastery.service;

import com.tarigor.javamastery.dto.Employee;

import java.util.List;

public interface IEmployeeService {
    int addEmployee(Employee employee);

    int deleteEmployee(int employeeId);

    boolean updateEmployeeData(Long id, Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);
}
