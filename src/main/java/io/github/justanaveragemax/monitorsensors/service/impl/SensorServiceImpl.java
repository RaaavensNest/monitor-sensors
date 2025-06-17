package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.request.SensorUpdateRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorCreationResponse;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import io.github.justanaveragemax.monitorsensors.entity.Sensor;
import io.github.justanaveragemax.monitorsensors.entity.SensorType;
import io.github.justanaveragemax.monitorsensors.entity.SensorUnit;
import io.github.justanaveragemax.monitorsensors.mapper.SensorMapper;
import io.github.justanaveragemax.monitorsensors.repository.SensorRepository;
import io.github.justanaveragemax.monitorsensors.service.SensorService;
import io.github.justanaveragemax.monitorsensors.service.SensorTypeService;
import io.github.justanaveragemax.monitorsensors.service.SensorUnitService;
import io.github.justanaveragemax.monitorsensors.util.ExceptionMessage;
import io.github.justanaveragemax.monitorsensors.util.search.SensorSearchCriteria;
import io.github.justanaveragemax.monitorsensors.util.search.SensorSpecificationBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorServiceImpl implements SensorService {

  private final SensorTypeService sensorTypeService;
  private final SensorUnitService sensorUnitService;

  private final SensorRepository sensorRepository;

  private final SensorMapper sensorMapper;

  @Override
  @Transactional(readOnly = true)
  public PagedModel<SensorResponse> findAll(final String name,
                                            final String model,
                                            @NonNull final Pageable pageable) {
    log.info("Fetching sensors with parameters: name ={}, model ={}, page={}, size={}, sort='{}'",
        name,
        model,
        pageable.getPageNumber(),
        pageable.getPageSize(), pageable.getSort());

    final SensorSearchCriteria criteria = new SensorSearchCriteria(name, model);
    Specification<Sensor> specification = SensorSpecificationBuilder.build(criteria);

    Page<Sensor> sensors = sensorRepository.findAll(specification, pageable);
    log.info("Found {} sensors", sensors.getNumberOfElements());

    return new PagedModel<>(sensors.map(sensorMapper::toSensorResponse));
  }

  @Override
  @Transactional(readOnly = true)
  public SensorResponse findById(@NonNull final Long id) {
    final Sensor sensor = findByIdOrThrow(id);

    return sensorMapper.toSensorResponse(sensor);
  }

  @Override
  public Sensor findByIdOrThrow(@NonNull final Long id) {
    log.info("Fetching sensor with id '{}'", id);

    final Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> {
      log.error(ExceptionMessage.SENSOR_NOT_FOUND.format(id));
      return new EntityNotFoundException(ExceptionMessage.SENSOR_NOT_FOUND.format(id));
    });

    log.info("Found sensor with id '{}': {}", id, sensor);
    return sensor;
  }

  @Override
  @Transactional
  public SensorCreationResponse create(@NonNull final SensorCreationRequest request) {
    log.info("Creating new sensor with parameters {}", request);

    final SensorType sensorType = sensorTypeService.findByNameOrThrow(request.getType());
    final SensorUnit sensorUnit = sensorUnitService.findByNameOrThrow(request.getUnit());

    final Sensor sensor = sensorMapper.toSensorEntity(request);

    sensor.setType(sensorType);
    sensor.setUnit(sensorUnit);

    sensorRepository.save(sensor);
    log.info("New sensor saved with id {}", sensor.getId());

    return sensorMapper.toSensorCreationResponse(sensor);
  }

  @Override
  @Transactional
  public SensorResponse update(@NonNull final Long id, @NonNull final SensorUpdateRequest request) {
    log.info("Updating sensor with id '{}'", id);

    Sensor sensor = findByIdOrThrow(id);

    sensor = sensorMapper.toSensorEntity(request, sensor);

    final SensorType sensorType = sensorTypeService.findByNameOrThrow(request.getType());
    final SensorUnit sensorUnit = sensorUnitService.findByNameOrThrow(request.getUnit());

    sensor.setType(sensorType);
    sensor.setUnit(sensorUnit);

    sensor = sensorRepository.save(sensor);

    log.info("Successfully updated sensor with id '{}': {}", id, sensor);

    return sensorMapper.toSensorResponse(sensor);
  }

  @Override
  @Transactional
  public void delete(@NonNull final Long id) {
    log.info("Deleting sensor with id '{}'", id);

    final Sensor sensor = findByIdOrThrow(id);

    sensorRepository.delete(sensor);

    log.info("Sensor successfully deleted");

  }

}
