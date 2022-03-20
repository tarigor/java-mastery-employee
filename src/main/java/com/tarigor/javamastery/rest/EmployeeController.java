package com.tarigor.javamastery.rest;

import com.tarigor.javamastery.dto.Employee;
import com.tarigor.javamastery.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Employee> updateEmployeeData(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployeeData(id, employee);
    }

    @GetMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam Map<String, String> employeeDetailsMap){
        String firstName = employeeDetailsMap.get("firstName");
        String lastName = employeeDetailsMap.get("lastName");
        return employeeService.findByFirstOrAndLastName(firstName,lastName);
    }
    @GetMapping(value = "/log")
    public String log() {
        log.trace("This is a TRACE level message");
        log.debug("This is a DEBUG level message");
        log.info("This is an INFO level message");
        log.warn("This is a WARN level message");
        log.error("This is an ERROR level message");
        return "See the log for details";
    }
}
