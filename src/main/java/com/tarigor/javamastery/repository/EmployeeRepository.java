package com.tarigor.javamastery.repository;

import com.tarigor.javamastery.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findEmployeeByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

}
