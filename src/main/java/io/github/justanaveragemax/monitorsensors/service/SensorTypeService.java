package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.entity.SensorType;
import java.util.Optional;

public interface SensorTypeService {

  Optional<SensorType> findByName(String name);

}
