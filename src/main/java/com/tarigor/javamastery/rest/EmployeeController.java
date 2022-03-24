package com.tarigor.javamastery.rest;

import com.tarigor.javamastery.entity.Employee;
import com.tarigor.javamastery.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "employee")
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:swagger.properties")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ApiOperation(value = "${add.employee.value}",
            notes = "${add.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of employee add", response = Employee.class),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "${delete.employee.value}",
            notes = "${delete.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of employee delete", response = Employee.class),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "${update.employees.value}",
            notes = "${update.employees.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of employee update", response = Employee.class),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity<Employee> updateEmployeeData(@PathVariable Long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployeeData(id, employee), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "${get.employees.value}",
            notes = "${get.employees.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting all employees", response = Employee.class),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "${get.employee.by.id.value}",
            notes = "${get.employee.by.id.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting employee by ID", response = Employee.class),
            @ApiResponse(code = 404, message = "An error occurred while getting employee by id"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/find")
    @ApiOperation(value = "${find.employee.value}",
            notes = "${find.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting employee by request parameters", response = Employee.class),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public ResponseEntity<List<Employee>> findEmployee(
            @ApiParam(
                    name = "employeeSearchParams",
                    type = "Map",
                    value = "Map of Employee parameters",
                    required = true)
            @RequestParam Map<String, String> employeeSearchParams) {
        return new ResponseEntity<>(employeeService.findByFirstOrAndLastName(employeeSearchParams), HttpStatus.OK);
    }
}
