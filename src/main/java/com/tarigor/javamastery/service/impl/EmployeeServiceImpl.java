package com.tarigor.javamastery.service.impl;

import com.tarigor.javamastery.dto.EmployeeDTO;
import com.tarigor.javamastery.entity.Employee;
import com.tarigor.javamastery.exception.ResourceNotFoundException;
import com.tarigor.javamastery.repository.EmployeeRepository;
import com.tarigor.javamastery.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
    }

    @Override
    public void deleteEmployee(Long id) {
        getEmployeeById(id);
        employeeRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public Employee updateEmployeeData(Long id, EmployeeDTO employeeDTO) {
        Employee employeeFromDB = getEmployeeById(id);
        return employeeRepository.save(updateEmployeeFromDB(employeeDTO, employeeFromDB));
    }

    private Employee updateEmployeeFromDB(EmployeeDTO employeeDTO, Employee employeeFromDB) {
        employeeFromDB.setFirstName(employeeDTO.getFirstName());
        employeeFromDB.setLastName(employeeDTO.getLastName());
        employeeFromDB.setDepartmentId(employeeDTO.getDepartmentId());
        employeeFromDB.setJobTitle(employeeDTO.getJobTitle());
        employeeFromDB.setGender(employeeDTO.getGender());
        employeeFromDB.setDateOfBirth(employeeDTO.getDateOfBirth());
        return employeeFromDB;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("an user with id->%d has been not found", id)));
    }

    @Override
    public List<Employee> findByFirstOrAndLastName(Map<String, String> employeeSearchParams) {
        String firstName = employeeSearchParams.get("firstName");
        String lastName = employeeSearchParams.get("lastName");
        return employeeRepository.findEmployeeByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }
}
