package com.tarigor.javamastery.service;

import com.tarigor.javamastery.dto.EmployeeDTO;
import com.tarigor.javamastery.entity.Employee;

public interface EmployeeServiceUtil {
    EmployeeDTO convertFromEntityToDto(Employee employee);

    Employee convertFromDtoToEntity(EmployeeDTO employeeDTO);
}
