package io.github.justanaveragemax.monitorsensors.mapper;

import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.request.SensorUpdateRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorCreationResponse;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import io.github.justanaveragemax.monitorsensors.entity.Sensor;

public interface SensorMapper {

  SensorResponse toSensorResponse(Sensor sensor);

  SensorCreationResponse toSensorCreationResponse(Sensor sensor);

  Sensor toSensorEntity(SensorCreationRequest request);

  Sensor toSensorEntity(SensorUpdateRequest request, Sensor entity);
}
