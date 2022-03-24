package com.tarigor.javamastery.service.impl;

import com.tarigor.javamastery.entity.Employee;
import com.tarigor.javamastery.exception.ResourceNotFoundException;
import com.tarigor.javamastery.repository.EmployeeRepository;
import com.tarigor.javamastery.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public com.tarigor.javamastery.entity.Employee addEmployee(com.tarigor.javamastery.entity.Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        getEmployeeById(id);
        employeeRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public com.tarigor.javamastery.entity.Employee updateEmployeeData(Long id, com.tarigor.javamastery.entity.Employee employee) {
        getEmployeeById(id);
        return employeeRepository.updateEmployee(employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth(),
                employee.getAge(),
                id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("an user with id->%d has been not found", id)));
    }

    @Override
    public List<Employee> findByFirstOrAndLastName(Map<String, String> employeeSearchParams) {
        String firstName = employeeSearchParams.get("firstName");
        String lastName = employeeSearchParams.get("lastName");
        return employeeRepository.findEmployeeByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }
}
