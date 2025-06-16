package io.github.justanaveragemax.monitorsensors.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DictionaryType {
  SENSOR_TYPE("type"),
  SENSOR_UNIT("unit");

  private final String value;
}
