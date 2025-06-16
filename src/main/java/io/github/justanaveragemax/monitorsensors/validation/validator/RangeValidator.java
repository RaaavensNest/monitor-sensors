package io.github.justanaveragemax.monitorsensors.validation.validator;

import io.github.justanaveragemax.monitorsensors.dto.resposnse.RangeDto;
import io.github.justanaveragemax.monitorsensors.validation.ValidRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<ValidRange, RangeDto> {

  @Override
  public boolean isValid(RangeDto value, ConstraintValidatorContext context) {
    if (value == null || value.getFrom() == null || value.getTo() == null) {
      return true;
    }
    return value.getFrom() < value.getTo();
  }
}
