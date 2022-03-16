package com.tarigor.javamastery.rest;

import com.tarigor.javamastery.dto.Employee;
import com.tarigor.javamastery.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public HttpStatus addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee) == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id) == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public HttpStatus updateEmployeeData(@RequestBody Employee employee) {
        return employeeService.updateEmployeeData(employee.getEmployeeId(), employee) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employeeFromDb = employeeService.getEmployeeById(id);
        if (employeeFromDb != null) {
            return new ResponseEntity<>(employeeFromDb, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
