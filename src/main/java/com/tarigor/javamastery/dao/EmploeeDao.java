package com.tarigor.javamastery.dao;

import com.tarigor.javamastery.dto.EmployeeDTO;

import java.util.List;

public interface EmploeeDao {
    EmployeeDTO addEmployee(EmployeeDTO employee);

    void deleteEmployee(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employee);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> findByPartOfFirstOrAndLastName(String firstName, String lastName);

}
