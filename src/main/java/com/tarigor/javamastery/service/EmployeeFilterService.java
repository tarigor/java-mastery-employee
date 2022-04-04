package com.tarigor.javamastery.service;

import com.tarigor.javamastery.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeFilterService {
    List<EmployeeDTO> findAll(Map<String, Object> filterParameters);
}
