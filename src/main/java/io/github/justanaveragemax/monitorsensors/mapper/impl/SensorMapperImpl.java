package io.github.justanaveragemax.monitorsensors.mapper.impl;

import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.request.SensorUpdateRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.RangeDto;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorCreationResponse;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import io.github.justanaveragemax.monitorsensors.entity.Sensor;
import io.github.justanaveragemax.monitorsensors.mapper.SensorMapper;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SensorMapperImpl implements SensorMapper {

  @Override
  public SensorResponse toSensorResponse(@NonNull final Sensor sensor) {
    final RangeDto range = RangeDto.builder()
        .from(sensor.getRangeFrom())
        .to(sensor.getRangeTo())
        .build();

    return SensorResponse.builder()
        .id(sensor.getId())
        .name(sensor.getName())
        .model(sensor.getModel())
        .range(range)
        .type(sensor.getType().getName())
        .unit(sensor.getUnit().getName())
        .location(sensor.getLocation())
        .description(sensor.getDescription())
        .build();
  }

  @Override
  public SensorCreationResponse toSensorCreationResponse(@NonNull final Sensor sensor) {
    return SensorCreationResponse.builder()
        .id(sensor.getId())
        .name(sensor.getName())
        .model(sensor.getModel())
        .build();
  }

  @Override
  public Sensor toSensorEntity(@NonNull final SensorCreationRequest request) {
    final RangeDto range = request.getRange();

    return Sensor.builder()
        .name(request.getName())
        .model(request.getModel())
        .rangeFrom(range.getFrom())
        .rangeTo(range.getTo())
        .location(request.getLocation())
        .description(request.getDescription())
        .build();
  }

  @Override
  public Sensor toSensorEntity(@NonNull final SensorUpdateRequest request, @NonNull final Sensor entity) {

    if(request.getName() != null) entity.setName(request.getName());
    if(request.getModel() != null) entity.setModel(request.getModel());

    final RangeDto range = request.getRange();
    if(range != null) {
      entity.setRangeFrom(range.getFrom());
      entity.setRangeTo(range.getTo());
    }

    if(request.getLocation() != null) entity.setLocation(request.getLocation());
    if(request.getDescription() != null) entity.setDescription(request.getDescription());

    return entity;
  }
}
