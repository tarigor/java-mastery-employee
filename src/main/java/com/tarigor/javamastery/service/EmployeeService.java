package com.tarigor.javamastery.service;

import com.tarigor.javamastery.dto.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<Employee> addEmployee(Employee employee);

    HttpStatus deleteEmployee(int employeeId);

    ResponseEntity<Employee> updateEmployeeData(Long id, Employee employee);

    List<Employee> getAllEmployees();

    ResponseEntity<Employee> getEmployeeById(Long id);

    ResponseEntity<List<Employee>> findByFirstOrAndLastName(String firstName, String lastName);
}
