package com.tarigor.javamastery.repository;

import com.tarigor.javamastery.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositorySpec extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
}
