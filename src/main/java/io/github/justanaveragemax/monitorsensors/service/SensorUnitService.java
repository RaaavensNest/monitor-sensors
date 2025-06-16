package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.entity.SensorUnit;
import java.util.Optional;

public interface SensorUnitService {

  Optional<SensorUnit> findByName(String name);

}
