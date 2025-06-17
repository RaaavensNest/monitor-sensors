package io.github.justanaveragemax.monitorsensors.util;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DictionaryType {
  SENSOR_TYPE("Type"),
  SENSOR_UNIT("Unit");

  private final String value;

  public static DictionaryType fromValue(String value){
    return Arrays.stream(values())
        .filter(d -> d.getValue().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.UNKNOWN_DICTIONARY_NAME.format(value)));
  }
}
