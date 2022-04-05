package com.tarigor.employeeservice.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOfBirthConstraint {

    String message() default "The age of the employee is too small";

    int ageLowLimit();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
