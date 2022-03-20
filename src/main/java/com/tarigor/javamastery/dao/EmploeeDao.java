package com.tarigor.javamastery.dao;

import com.tarigor.javamastery.dto.Employee;

import java.util.List;

public interface EmploeeDao {
    int addEmployee(Employee employee);

    int deleteEmployee(int id);

    int updateEmployee(Long id, Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> findByPartOfFirstOrAndLastName(String firstName, String lastName);

}
