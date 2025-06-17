package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.request.SensorUpdateRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorCreationResponse;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import io.github.justanaveragemax.monitorsensors.entity.Sensor;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface SensorService {

  PagedModel<SensorResponse> findAll(Pageable pageable);

  SensorResponse findById(Long id);

  Sensor findByIdOrThrow(Long id);

  SensorCreationResponse create(SensorCreationRequest request);

  SensorResponse update(Long id, SensorUpdateRequest request);

  void delete(Long id);
}
