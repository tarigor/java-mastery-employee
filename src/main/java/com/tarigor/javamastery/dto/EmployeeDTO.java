package com.tarigor.javamastery.dto;

import com.tarigor.javamastery.dto.validation.DateOfBirthConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {
    @NotBlank(message = "First Name presence is mandatory!")
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "The format of name is not valid:1-Must contain only letters.2-Start with a capital letter.3-The number of letters is at least 2")
    private String firstName;
    @NotBlank(message = "Last Name presence is mandatory!")
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "The format of name is not valid:1-Must contain only letters.2-Start with a capital letter.3-The number of letters is at least 2")
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private String gender;
    @DateOfBirthConstraint(ageLowLimit = 18)
    private Date dateOfBirth;
}
