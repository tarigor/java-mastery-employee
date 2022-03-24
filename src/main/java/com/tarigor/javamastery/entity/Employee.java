package com.tarigor.javamastery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private Long employeeId;
    @NotBlank(message = "First Name is mandatory!")
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "The format of name is not valid:1-Must contain only letters.2-Start with a capital letter.3-The number of letters is at least 2")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory!")
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "The format of name is not valid:1-Must contain only letters.2-Start with a capital letter.3-The number of letters is at least 2")
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private String gender;
    private Date dateOfBirth;
    @Min(value = 18, message = "The minimum allowed age is 18 years old")
    private int age;
}