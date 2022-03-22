package com.tarigor.javamastery.dao.impl;

import com.tarigor.javamastery.dao.EmploeeDao;
import com.tarigor.javamastery.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmploeeDao {

    public static final String QUERY_ADD_EMPLOYEE = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES(?,?,?,?,?,?) RETURNING *";
    public static final String QUERY_DELETE_EMPLOYEE = "delete from employee where employee_id = ?";
    public static final String QUERY_UPDATE_EMPLOYEE = "UPDATE employee SET first_name =? , last_name =?, department_id=?, job_title=?, gender=?, date_of_birth=? where employee_id=? RETURNING*";
    public static final String QUERY_GET_ALL_EMPLOYEES = "select * from employee";
    public static final String QUERY_GET_EMPLOYEE_BY_ID = "select * from employee where employee_id = ?";
    private static final String QUERY_GET_EMPLOYEE_BY_PART_OF_CREDENTIALS = "select * from employee where first_name like ? and last_name like ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Employee addEmployee(Employee employee) {
        return jdbcTemplate.queryForObject(QUERY_ADD_EMPLOYEE, BeanPropertyRowMapper.newInstance(Employee.class),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth(),
                employee.getAge()
        );
    }

    @Override
    public void deleteEmployee(Long id) {
        jdbcTemplate.update(QUERY_DELETE_EMPLOYEE, id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        return jdbcTemplate.queryForObject(QUERY_UPDATE_EMPLOYEE, BeanPropertyRowMapper.newInstance(Employee.class),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth(),
                employee.getAge(),
                id
        );
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(QUERY_GET_ALL_EMPLOYEES, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return jdbcTemplate.queryForObject(QUERY_GET_EMPLOYEE_BY_ID, BeanPropertyRowMapper.newInstance(Employee.class), id);
    }

    @Override
    public List<Employee> findByPartOfFirstOrAndLastName(String firstName, String lastName) {
        String firstNamePart = "%" + firstName + "%";
        String lastNamePart = "%" + lastName + "%";
        return jdbcTemplate.query(
                QUERY_GET_EMPLOYEE_BY_PART_OF_CREDENTIALS,
                BeanPropertyRowMapper.newInstance(Employee.class),
                firstNamePart,
                lastNamePart);
    }
}
