package com.tarigor.javamastery.dao.impl;

import com.tarigor.javamastery.dao.EmploeeDao;
import com.tarigor.javamastery.dto.Employee;
import com.tarigor.javamastery.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmploeeDao {

    public static final String QUERY_ADD_EMPLOYEE = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES(?,?,?,?,?,?)";
    public static final String QUERY_DELETE_EMPLOYEE = "delete from employee where employee_id = ?";
    public static final String QUERY_UPDATE_EMPLOYEE = "UPDATE employee SET first_name =? , last_name =?, department_id=?, job_title=?, gender=?, date_of_birth=? where employee_id=?";
    public static final String QUERY_GET_ALL_EMPLOYEES = "select * from employee";
    public static final String QUERY_GET_EMPLOYEE_BY_ID = "select * from employee where employee_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(QUERY_ADD_EMPLOYEE, new String[]{"employee_id"});
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setLong(3, employee.getDepartmentId());
            ps.setString(4, employee.getJobTitle());
            ps.setString(5, employee.getGender());
            ps.setDate(6, employee.getDateOfBirth());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
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
            throw new EmployeeNotFoundException("");
        }
        return employee;
    }
}
