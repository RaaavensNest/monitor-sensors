package io.github.justanaveragemax.monitorsensors.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
  SENSOR_NOT_FOUND("No sensor found with id '%s'"),
  DICTIONARY_VALUE_NOT_FOUND("%s with name '%s' not found"),
  INVALID_DICTIONARY_VALUE("Invalid %s: '%s'. Allowed values: [%s]"),
  UNKNOWN_DICTIONARY_NAME("Unknown dictionary name provided: %s");

  private final String message;

  public String format(Object... args) {
    return String.format(message, args);
  }
}

