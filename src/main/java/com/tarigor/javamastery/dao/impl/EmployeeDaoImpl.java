package com.tarigor.javamastery.dao.impl;

import com.tarigor.javamastery.dao.IEmploeeDao;
import com.tarigor.javamastery.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements IEmploeeDao {

    public static final String QUERY_ADD_EMPLOYEE = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES(?,?,?,?,?,?)";
    public static final String QUERY_DELETE_EMPLOYEE = "delete from employee where employee_id = ?";
    public static final String QUERY_UPDATE_EMPLOYEE = "UPDATE employee SET first_name =? , last_name =?, department_id=?, job_title=?, gender=?, date_of_birth=? where employee_id=?";
    public static final String QUERY_GET_ALL_EMPLOYEES = "select * from employee";
    public static final String QUERY_GET_EMPLOYEE_BY_ID = "select * from employee where employee_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addEmployee(Employee employee) {
        return jdbcTemplate.update(QUERY_ADD_EMPLOYEE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth());
    }

    @Override
    public int deleteEmployee(int id) {
        return jdbcTemplate.update(QUERY_DELETE_EMPLOYEE, id);
    }

    @Override
    public int updateEmployee(Long id, Employee employee) {
        return jdbcTemplate.update(QUERY_UPDATE_EMPLOYEE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth(),
                id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(QUERY_GET_ALL_EMPLOYEES, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee;
        try {
            employee = jdbcTemplate.queryForObject(QUERY_GET_EMPLOYEE_BY_ID, BeanPropertyRowMapper.newInstance(Employee.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return employee;
    }
}
