package io.github.justanaveragemax.monitorsensors.util.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchableSensorField {
  NAME("name"),
  MODEL("model");

  private final String value;

}
