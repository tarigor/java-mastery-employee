package com.tarigor.employeeservice.service;

import com.tarigor.employeeservice.dto.EmployeeDTO;
import com.tarigor.employeeservice.entity.Employee;

public interface EmployeeServiceUtil {
    EmployeeDTO convertFromEntityToDto(Employee employee);

    Employee convertFromDtoToEntity(EmployeeDTO employeeDTO);
}
