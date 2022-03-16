package com.tarigor.javamastery.dao;

import com.tarigor.javamastery.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//- добавлять новых сотрудников;
//- удалять уволенных;
//- обновлять данные о сотрудниках;
//- а также должна быть возможность просмотреть весь список сотрудников сразу;
//- отдельно получить каждого сотрудника.

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addEmployee(Employee employee) {
        String query = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(query,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth());
    }

    public int deleteEmployee(int id) {
        String query = "delete from employee where employee_id = ?";
        return jdbcTemplate.update(query, id);
    }

    public int updateEmployee(Long id, Employee employee) {
        String query = "UPDATE employee SET first_name =? , last_name =?, department_id=?, job_title=?, gender=?, date_of_birth=? where employee_id=?";
        return jdbcTemplate.update(query,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth(),
                id);
    }

    public List<Employee> getAllEmployees() {
        String query = "select * from employee";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    public Employee getEmployeeById(Long id) {
        String query = "select * from employee where employee_id = ?";
        Employee employee;
        try {
            employee = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Employee.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return employee;
    }
}
