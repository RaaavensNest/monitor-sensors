package io.github.justanaveragemax.monitorsensors.validation;

import io.github.justanaveragemax.monitorsensors.util.DictionaryType;
import io.github.justanaveragemax.monitorsensors.validation.validator.DictionaryValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DictionaryValueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDictionaryValue {
  String message() default "Invalid sensor type";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  DictionaryType dictionary();
}
