package com.tarigor.javamastery.dao.impl;

import com.tarigor.javamastery.dao.EmploeeDao;
import com.tarigor.javamastery.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmploeeDao {

    public static final String QUERY_ADD_EMPLOYEE = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES(?,?,?,?,?,?) RETURNING *";
    public static final String QUERY_DELETE_EMPLOYEE = "delete from employee where employee_id = ?";
    public static final String QUERY_UPDATE_EMPLOYEE = "UPDATE employee SET first_name =? , last_name =?, department_id=?, job_title=?, gender=?, date_of_birth=? where employee_id=? RETURNING*";
    public static final String QUERY_GET_ALL_EMPLOYEES = "select * from employee";
    public static final String QUERY_GET_EMPLOYEE_BY_ID = "select * from employee where employee_id = ?";
    private static final String QUERY_GET_EMPLOYEE_BY_PART_OF_CREDENTIALS = "select * from employee where first_name like ? and last_name like ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        return jdbcTemplate.queryForObject(QUERY_ADD_EMPLOYEE, BeanPropertyRowMapper.newInstance(EmployeeDTO.class),
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
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employee) {
        return jdbcTemplate.queryForObject(QUERY_UPDATE_EMPLOYEE, BeanPropertyRowMapper.newInstance(EmployeeDTO.class),
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
    public List<EmployeeDTO> getAllEmployees() {
        return jdbcTemplate.query(QUERY_GET_ALL_EMPLOYEES, BeanPropertyRowMapper.newInstance(EmployeeDTO.class));
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return jdbcTemplate.queryForObject(QUERY_GET_EMPLOYEE_BY_ID, BeanPropertyRowMapper.newInstance(EmployeeDTO.class), id);
    }

    @Override
    public List<EmployeeDTO> findByPartOfFirstOrAndLastName(String firstName, String lastName) {
        String firstNamePart = "%" + firstName + "%";
        String lastNamePart = "%" + lastName + "%";
        return jdbcTemplate.query(
                QUERY_GET_EMPLOYEE_BY_PART_OF_CREDENTIALS,
                BeanPropertyRowMapper.newInstance(EmployeeDTO.class),
                firstNamePart,
                lastNamePart);
    }
}
