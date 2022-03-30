package com.tarigor.javamastery.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, Date> {
    private int ageLowLimit;

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Date, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(DateOfBirthConstraint constraintAnnotation) {
        this.ageLowLimit = constraintAnnotation.ageLowLimit();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return ChronoUnit.YEARS.between(value.toLocalDate(), LocalDate.now()) > ageLowLimit;
    }
}
