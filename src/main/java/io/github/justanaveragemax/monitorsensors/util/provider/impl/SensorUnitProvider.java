package io.github.justanaveragemax.monitorsensors.util.provider.impl;

import io.github.justanaveragemax.monitorsensors.repository.SensorUnitRepository;
import io.github.justanaveragemax.monitorsensors.util.DictionaryType;
import io.github.justanaveragemax.monitorsensors.util.provider.DictionaryProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorUnitProvider implements DictionaryProvider {

  private final SensorUnitRepository sensorUnitRepository;

  @Override
  public DictionaryType getDictionaryType() {
    return DictionaryType.SENSOR_UNIT;
  }

  @Override
  public List<String> getAvailableValues() {
    return sensorUnitRepository.findAll().stream()
        .map(unit -> unit.getName().toUpperCase())
        .toList();
  }
}
