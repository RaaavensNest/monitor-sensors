package io.github.justanaveragemax.monitorsensors.mapper;

import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import io.github.justanaveragemax.monitorsensors.entity.Sensor;

public interface SensorMapper {

  SensorResponse toSensorResponse(Sensor sensor);

  Sensor toSensorEntity(SensorCreationRequest request);
}
