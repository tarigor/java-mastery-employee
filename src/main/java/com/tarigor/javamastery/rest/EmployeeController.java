package com.tarigor.javamastery.rest;

import com.tarigor.javamastery.dto.EmployeeDTO;
import com.tarigor.javamastery.entity.Employee;
import com.tarigor.javamastery.service.EmployeeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "employee-management")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/employees")
    @ApiOperation(value = "${add.employee.value}", notes = "${add.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of employee add", response = EmployeeDTO.class),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("addEmployee: the following employeeDTO data received for adding -> {}", employeeDTO.toString());
        return new ResponseEntity<>(employeeService.addEmployee(employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{id}")
    @ApiOperation(value = "${delete.employee.value}", notes = "${delete.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of employee delete", response = Employee.class),
            @ApiResponse(code = 404, message = "An employee with such id was not found and can't deleted"),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        log.info("deleteEmployee: the following employee ID requested to be deleted -> {}", id);
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/employees/{id}")
    @ApiOperation(value = "${update.employees.value}", notes = "${update.employees.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of employee update", response = Employee.class),
            @ApiResponse(code = 404, message = "An employee with such id was not found and can't updated"),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    public ResponseEntity<Employee> updateEmployeeData(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("updateEmployee: the following data of employee with id -> {} requested to be updated -> {}", id, employeeDTO);
        return new ResponseEntity<>(employeeService.updateEmployeeData(id, employeeDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/employees")
    @ApiOperation(value = "${get.employees.value}", notes = "${get.employees.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting all employees", response = Employee.class),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{id}")
    @ApiOperation(value = "${get.employee.by.id.value}", notes = "${get.employee.by.id.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting employee by ID", response = Employee.class),
            @ApiResponse(code = 404, message = "An error occurred while getting employee by id"),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("getEmployeeById: the following data of employee with id -> {} was requested", id);
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/employees/employees")
    @ApiOperation(value = "${find.employee.value}", notes = "${find.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting employee by request parameters", response = Employee.class),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "firstName", type = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "lastName", type = "String", required = true, paramType = "query")
    })
    public ResponseEntity<List<Employee>> findEmployee(@RequestParam @ApiParam(hidden = true) Map<String, String> employeeSearchParams) {
        employeeSearchParams.forEach((key, value) -> log.info("findEmployee:the following parameter name={} with value={} was requested to search an employee", key, value));
        return new ResponseEntity<>(employeeService.findByFirstOrAndLastName(employeeSearchParams), HttpStatus.OK);
    }
}
