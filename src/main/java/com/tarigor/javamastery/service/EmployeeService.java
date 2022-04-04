package com.tarigor.javamastery.service;

import com.tarigor.javamastery.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long employeeId);

    EmployeeDTO updateEmployeeData(Long id, EmployeeDTO employee);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> findByFirstOrAndLastName(String firstName, String lastName);

}
