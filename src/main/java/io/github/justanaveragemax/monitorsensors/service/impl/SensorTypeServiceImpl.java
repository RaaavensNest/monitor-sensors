package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.entity.SensorType;
import io.github.justanaveragemax.monitorsensors.repository.SensorTypeRepository;
import io.github.justanaveragemax.monitorsensors.service.SensorTypeService;
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
public class SensorTypeServiceImpl implements SensorTypeService {

  private final SensorTypeRepository sensorTypeRepository;

  @Override
  public SensorType findByNameOrThrow(@NonNull final String name) {
    final SensorType sensorType = sensorTypeRepository.findByName(name).orElseThrow(() -> {
      final String exceptionMessage = ExceptionMessage.DICTIONARY_VALUE_NOT_FOUND.format(DictionaryType.SENSOR_TYPE,
          name);
      log.error(exceptionMessage);
      return new EntityNotFoundException(exceptionMessage);
    });

    log.info("Found sensor type by name '{}': {}", name, sensorType);
    return sensorType;
  }
}
