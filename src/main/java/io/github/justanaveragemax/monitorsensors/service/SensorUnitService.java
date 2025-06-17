package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.entity.SensorUnit;

public interface SensorUnitService {

  SensorUnit findByNameOrThrow(String name);

}
