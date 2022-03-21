package com.tarigor.javamastery.service.impl;

import com.tarigor.javamastery.dao.impl.EmployeeDaoImpl;
import com.tarigor.javamastery.dto.Employee;
import com.tarigor.javamastery.exception.ResourceNotFoundException;
import com.tarigor.javamastery.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        getEmployeeById(id);
        employeeDao.deleteEmployee(id);
    }

    @Override
    public Employee updateEmployeeData(Long id, Employee employee) {
        getEmployeeById(id);
        return employeeDao.updateEmployee(id, employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        try {
            return employeeDao.getEmployeeById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("an user with id->%d has been not found", id));
        }
    }

    @Override
    public List<Employee> findByFirstOrAndLastName(Map<String, String> employeeDetailsMap) {
        String firstName = employeeDetailsMap.get("firstName");
        String lastName = employeeDetailsMap.get("lastName");
        return employeeDao.findByPartOfFirstOrAndLastName(firstName, lastName);
    }
}
