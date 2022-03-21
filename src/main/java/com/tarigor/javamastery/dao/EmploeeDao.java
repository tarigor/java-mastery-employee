package com.tarigor.javamastery.dao;

import com.tarigor.javamastery.dto.Employee;

import java.util.List;

public interface EmploeeDao {
    Employee addEmployee(Employee employee);

    void deleteEmployee(Long id);

    Employee updateEmployee(Long id, Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> findByPartOfFirstOrAndLastName(String firstName, String lastName);

}
