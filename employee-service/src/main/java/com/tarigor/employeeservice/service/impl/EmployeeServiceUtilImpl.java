package com.tarigor.employeeservice.service.impl;

import com.tarigor.employeeservice.dto.EmployeeDTO;
import com.tarigor.employeeservice.entity.Employee;
import com.tarigor.employeeservice.service.EmployeeServiceUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeServiceUtilImpl implements EmployeeServiceUtil {

    private final ModelMapper modelMapper;

    @Override
    public EmployeeDTO convertFromEntityToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public Employee convertFromDtoToEntity(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
}
