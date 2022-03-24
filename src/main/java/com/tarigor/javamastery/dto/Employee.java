package com.tarigor.javamastery.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private Long employeeId;
    @NotBlank(message = "First Name is mandatory!")
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "The format of name is not valid:1-Must contain only letters.2-Start with a capital letter.3-The number of letters is at least 2")
    @ApiModelProperty(
            value = "first name of the user",
            name = "firstName",
            dataType = "String",
            example = "Ivan")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory!")
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "The format of name is not valid:1-Must contain only letters.2-Start with a capital letter.3-The number of letters is at least 2")
    @ApiModelProperty(
            value = "last name of the user",
            name = "lastName",
            dataType = "String",
            example = "Ivanov")
    private String lastName;
    @ApiModelProperty(
            value = "department ID belongs to an employee",
            name = "departmnetId",
            dataType = "Long",
            example = "1")
    private Long departmentId;
    @ApiModelProperty(
            value = "job title of employee",
            name = "jobTitle",
            dataType = "String",
            example = "tester")
    private String jobTitle;
    @ApiModelProperty(
            value = "gender of employee",
            name = "gender",
            dataType = "String",
            example = "female")
    private String gender;
    @ApiModelProperty(
            value = "date of birth of employee",
            name = "dateOfBirth",
            dataType = "Date",
            example = "2013-03-07")
    private Date dateOfBirth;
    @Min(value = 18, message = "The minimum allowed age is 18 years old")
    @ApiModelProperty(
            value = "age of employee",
            name = "age",
            dataType = "Integer",
            example = "18")
    private int age;
}
