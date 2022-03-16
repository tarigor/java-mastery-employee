package com.tarigor.javamastery.service.impl;

import com.tarigor.javamastery.dao.EmployeeDao;
import com.tarigor.javamastery.dto.Employee;
import com.tarigor.javamastery.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public int deleteEmployee(int employeeId) {
        return employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public boolean updateEmployeeData(Long id, Employee employee) {
        Employee employee1FromDB = employeeDao.getEmployeeById(id);
        if (employee1FromDB != null) {
            employee1FromDB.setFirstName(employee.getFirstName());
            employee1FromDB.setLastName(employee.getLastName());
            employee1FromDB.setDepartmentId(employee.getDepartmentId());
            employee1FromDB.setJobTitle(employee.getJobTitle());
            employee1FromDB.setGender(employee.getGender());
            employee1FromDB.setDateOfBirth(employee.getDateOfBirth());
            return employeeDao.updateEmployee(id, employee1FromDB) != 0;
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDao.getEmployeeById(id);
    }
}
