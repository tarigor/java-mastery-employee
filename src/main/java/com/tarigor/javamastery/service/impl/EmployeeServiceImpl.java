package com.tarigor.javamastery.service.impl;

import com.tarigor.javamastery.dto.EmployeeDTO;
import com.tarigor.javamastery.exception.ResourceNotFoundException;
import com.tarigor.javamastery.repository.EmployeeRepository;
import com.tarigor.javamastery.service.EmployeeService;
import com.tarigor.javamastery.service.EmployeeServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeServiceUtil employeeServiceUtil;

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        return employeeServiceUtil.convertFromEntityToDto(employeeRepository.save(employeeServiceUtil.convertFromDtoToEntity(employeeDTO)));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.delete(employeeServiceUtil.convertFromDtoToEntity(getEmployeeById(id)));
    }

    @Override
    public EmployeeDTO updateEmployeeData(Long id, EmployeeDTO employeeDTO) {
        EmployeeDTO employeeFromDB = getEmployeeById(id);
        BeanUtils.copyProperties(employeeDTO, employeeFromDB);
        return employeeServiceUtil.convertFromEntityToDto(employeeRepository.save(employeeServiceUtil.convertFromDtoToEntity(employeeFromDB)));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeServiceUtil::convertFromEntityToDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeServiceUtil.convertFromEntityToDto(employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("an user with id->%d has been not found", id))));
    }

    @Override
    public List<EmployeeDTO> findByFirstOrAndLastName(String firstName, String lastName) {
        return employeeRepository.findEmployeeByFirstNameContainingAndLastNameContaining(firstName, lastName)
                .stream()
                .map(employeeServiceUtil::convertFromEntityToDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
