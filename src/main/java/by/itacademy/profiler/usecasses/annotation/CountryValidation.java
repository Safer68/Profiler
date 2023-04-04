package by.itacademy.profiler.usecasses.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CountryValidator.class)
public @interface CountryValidation {

    String message() default "Invalid id: country not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
