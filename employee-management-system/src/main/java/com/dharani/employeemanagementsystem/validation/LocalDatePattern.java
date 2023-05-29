package com.dharani.employeemanagementsystem.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = LocalDatePattern.LocalDatePatternValidator.class)
@Documented
public @interface LocalDatePattern {

    String message() default "Invalid date format. Use dd/MM/yyyy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class LocalDatePatternValidator implements ConstraintValidator<LocalDatePattern, LocalDate> {

        private static final String DATE_PATTERN = "dd/MM/yyyy";

        @Override
        public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
            if (value == null) {
                return true; // Null values are handled by @NotNull annotation
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
                String formattedDate = value.format(formatter);
                LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
                return value.equals(parsedDate);
            } catch (Exception e) {
                return false;
            }
        }
    }
}

