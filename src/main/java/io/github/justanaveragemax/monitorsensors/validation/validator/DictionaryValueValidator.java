package io.github.justanaveragemax.monitorsensors.validation.validator;

import io.github.justanaveragemax.monitorsensors.service.DictionaryService;
import io.github.justanaveragemax.monitorsensors.util.ExceptionMessage;
import io.github.justanaveragemax.monitorsensors.validation.ValidDictionaryValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DictionaryValueValidator implements ConstraintValidator<ValidDictionaryValue, String> {

  private final DictionaryService dictionaryService;

  private String dictionary;

  @Override
  public void initialize(@NonNull final ValidDictionaryValue annotation) {
    this.dictionary = annotation.dictionary().getValue();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty() || dictionaryService.isValueValid(dictionary, value)) {
      return true;
    }

    context.disableDefaultConstraintViolation();

    final String message = ExceptionMessage.INVALID_DICTIONARY_VALUE.format(dictionary, value,
        String.join(", ", dictionaryService.getAvailableValues(dictionary)));

    context.buildConstraintViolationWithTemplate(message)
        .addConstraintViolation();

    return false;
  }
}
