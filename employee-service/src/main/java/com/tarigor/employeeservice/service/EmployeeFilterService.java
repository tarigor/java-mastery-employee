package com.tarigor.employeeservice.service;

import com.tarigor.employeeservice.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeFilterService {
    List<EmployeeDTO> findAll(Map<String, Object> filterParameters);
}
