package com.tarigor.javamastery.dao;

import com.tarigor.javamastery.dto.Employee;

import java.util.List;

public interface IEmploeeDao {
    int addEmployee(Employee employee);

    int deleteEmployee(int id);

    int updateEmployee(Long id, Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);
}
