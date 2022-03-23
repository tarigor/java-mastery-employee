package com.tarigor.javamastery.repository;

import com.tarigor.javamastery.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("update Employee set " +
            "firstName = :firstName, " +
            "lastName = :lastName, " +
            "departmentId = :departmentId, " +
            "jobTitle = :jobTitle, " +
            "gender = :gender, " +
            "dateOfBirth = :dateOfBirth, " +
            "age = :age " +
            "where employeeId = :employeeId")
    Employee updateEmployee(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("departmentId") Long departmentId,
            @Param("jobTitle") String jobTitle,
            @Param("gender") String gender,
            @Param("dateOfBirth") Date dateOfBirth,
            @Param("age") Integer age,
            @Param("employeeId") Long employeeId);

    Optional<Employee> getEmployeeByEmployeeId(Long id);

    List<Employee> findEmployeeByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

}
