package com.tarigor.javamastery.rest;

import com.tarigor.javamastery.dto.EmployeeDTO;
import com.tarigor.javamastery.entity.Employee;
import com.tarigor.javamastery.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "employees")
@RequiredArgsConstructor
@Validated
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ApiOperation(value = "${add.employee.value}", notes = "${add.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "${addEmployee.message.200}", response = EmployeeDTO.class),
            @ApiResponse(code = 500, message = "${addEmployee.message.500}")}
    )
    public EmployeeDTO addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("addEmployee: the following employeeDTO data received for adding -> {}", employeeDTO.toString());
        return employeeService.addEmployee(employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "${delete.employee.value}", notes = "${delete.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "${deleteEmployee.message.200}", response = Employee.class),
            @ApiResponse(code = 404, message = "${deleteEmployee.message.404}"),
            @ApiResponse(code = 500, message = "${deleteEmployee.message.500}")}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable @Min(0) Long id) {
        log.info("deleteEmployee: the following employee ID requested to be deleted -> {}", id);
        employeeService.deleteEmployee(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "${update.employees.value}", notes = "${update.employees.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "updateEmployeeData.message.200", response = Employee.class),
            @ApiResponse(code = 404, message = "updateEmployeeData.message.404"),
            @ApiResponse(code = 500, message = "updateEmployeeData.message.500")}
    )
    public EmployeeDTO updateEmployeeData(@PathVariable @Min(0) Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("updateEmployee: the following data of employee with id -> {} requested to be updated -> {}", id, employeeDTO);
        return employeeService.updateEmployeeData(id, employeeDTO);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "${get.employee.by.id.value}", notes = "${get.employee.by.id.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting employee by ID", response = Employee.class),
            @ApiResponse(code = 404, message = "An error occurred while getting employee by id"),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    public EmployeeDTO getEmployeeById(@PathVariable @Min(value = 1, message = "must be above 0") Long id) {
        log.info("getEmployeeById: the following data of employee with id -> {} was requested", id);
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    @ApiOperation(value = "${find.employee.value}", notes = "${find.employee.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful of getting employee by request parameters", response = Employee.class),
            @ApiResponse(code = 500, message = "An error occurred on the server side")}
    )
    public List<EmployeeDTO> findEmployee(
            @RequestParam(required = false, defaultValue = "") @ApiParam String firstName,
            @RequestParam(required = false, defaultValue = "") @ApiParam String lastName) {
        log.info("findEmployee: find employee(s) with the such parameters firstName={} and lastName={} is requested", firstName, lastName);
        return employeeService.findByFirstOrAndLastName(firstName, lastName);
    }

}
