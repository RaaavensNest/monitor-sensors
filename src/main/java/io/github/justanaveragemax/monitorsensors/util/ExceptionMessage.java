package io.github.justanaveragemax.monitorsensors.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
  ENTITY_NOT_FOUND("%s with with name '%s' not found"),
  INVALID_DICTIONARY_VALUE("Invalid %s: '%s'. Allowed values: [%s]");

  private final String message;

  public String format(Object... args) {
    return String.format(message, args);
  }
}

