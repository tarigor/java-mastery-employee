package com.tarigor.javamastery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private String gender;
    private Date dateOfBirth;
}
