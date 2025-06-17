package io.github.justanaveragemax.monitorsensors.util.provider.impl;

import io.github.justanaveragemax.monitorsensors.repository.SensorTypeRepository;
import io.github.justanaveragemax.monitorsensors.util.DictionaryType;
import io.github.justanaveragemax.monitorsensors.util.provider.DictionaryProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorTypeProvider implements DictionaryProvider {

  private final SensorTypeRepository sensorTypeRepository;

  @Override
  public DictionaryType getDictionaryType() {
    return DictionaryType.SENSOR_TYPE;
  }

  @Override
  public List<String> getAvailableValues() {
    return sensorTypeRepository.findAll().stream()
        .map(type -> type.getName().toUpperCase())
        .toList();
  }
}
