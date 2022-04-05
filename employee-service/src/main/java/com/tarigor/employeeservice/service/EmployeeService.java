package com.tarigor.employeeservice.service;

import com.tarigor.employeeservice.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long employeeId);

    EmployeeDTO updateEmployeeData(Long id, EmployeeDTO employee);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> findByFirstOrAndLastName(String firstName, String lastName);

}
