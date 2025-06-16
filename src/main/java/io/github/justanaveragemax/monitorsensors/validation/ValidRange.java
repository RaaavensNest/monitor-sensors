package io.github.justanaveragemax.monitorsensors.validation;

import io.github.justanaveragemax.monitorsensors.validation.validator.RangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRange {
  String message() default "'from' must be less than 'to'";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
