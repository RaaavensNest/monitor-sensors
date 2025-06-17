package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.entity.SensorUnit;
import io.github.justanaveragemax.monitorsensors.repository.SensorUnitRepository;
import io.github.justanaveragemax.monitorsensors.service.SensorUnitService;
import io.github.justanaveragemax.monitorsensors.util.DictionaryType;
import io.github.justanaveragemax.monitorsensors.util.ExceptionMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorUnitServiceImpl implements SensorUnitService {

  private final SensorUnitRepository sensorUnitRepository;

  @Override
  public SensorUnit findByNameOrThrow(@NonNull final String name) {
    final SensorUnit sensorUnit = sensorUnitRepository.findByName(name).orElseThrow(() -> {
      final String exceptionMessage = ExceptionMessage.DICTIONARY_VALUE_NOT_FOUND.format(DictionaryType.SENSOR_UNIT,
          name);
      log.error(exceptionMessage);
      return new EntityNotFoundException(exceptionMessage);
    });

    log.info("Found sensor unit by name '{}': {}", name, sensorUnit);
    return sensorUnit;
  }
}
