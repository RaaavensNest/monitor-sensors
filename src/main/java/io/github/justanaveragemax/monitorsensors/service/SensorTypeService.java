package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.entity.SensorType;

public interface SensorTypeService {

  SensorType findByNameOrThrow(String name);

}
