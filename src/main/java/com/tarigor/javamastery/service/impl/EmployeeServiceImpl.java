package com.tarigor.javamastery.service.impl;

import com.tarigor.javamastery.dao.impl.EmployeeDaoImpl;
import com.tarigor.javamastery.dto.Employee;
import com.tarigor.javamastery.exception.ErrorWhileAddEmployeeException;
import com.tarigor.javamastery.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    @Override
    public ResponseEntity<Employee> addEmployee(Employee employee) {
        int id = employeeDao.addEmployee(employee);
        System.out.println("id - > " + id);
        if (id != 0) {
            return new ResponseEntity<>(employeeDao.getEmployeeById((long) id), HttpStatus.OK);
        }
        throw new ErrorWhileAddEmployeeException("an error occur while an employee insert");
    }

    @Override
    public HttpStatus deleteEmployee(int employeeId) {
        return employeeDao.deleteEmployee(employeeId) == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }

    @Override
    public ResponseEntity<Employee> updateEmployeeData(Long id, Employee employee) {
        Employee employee1FromDB = employeeDao.getEmployeeById(id);
        if (employee1FromDB != null) {
            employee1FromDB.setFirstName(employee.getFirstName());
            employee1FromDB.setLastName(employee.getLastName());
            employee1FromDB.setDepartmentId(employee.getDepartmentId());
            employee1FromDB.setJobTitle(employee.getJobTitle());
            employee1FromDB.setGender(employee.getGender());
            employee1FromDB.setDateOfBirth(employee.getDateOfBirth());
            int insertedId = employeeDao.updateEmployee(id, employee1FromDB);
            return new ResponseEntity<>(employeeDao.getEmployeeById((long) insertedId), HttpStatus.OK);
        }
        throw new ErrorWhileAddEmployeeException("an error occur while an employee update");
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long id) {
        return new ResponseEntity<>(employeeDao.getEmployeeById(id), HttpStatus.OK);
    }
}
