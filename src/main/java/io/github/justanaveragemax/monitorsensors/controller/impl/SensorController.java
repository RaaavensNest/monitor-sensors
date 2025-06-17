package io.github.justanaveragemax.monitorsensors.controller.impl;

import io.github.justanaveragemax.monitorsensors.controller.SensorApi;
import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.request.SensorUpdateRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorCreationResponse;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import io.github.justanaveragemax.monitorsensors.service.SensorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SensorController implements SensorApi {

  private final SensorService sensorService;

  @Override
  public ResponseEntity<PagedModel<SensorResponse>> findAll(final String name, final String model,
                                                            @NonNull final Pageable pageable) {
    return ResponseEntity.ok(sensorService.findAll(name, model, pageable));
  }

  @Override
  public ResponseEntity<SensorResponse> findById(@NonNull final Long id) {
    return ResponseEntity.ok(sensorService.findById(id));
  }

  @Override
  public ResponseEntity<SensorCreationResponse> create(@NonNull final SensorCreationRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(sensorService.create(request));
  }

  @Override
  public ResponseEntity<SensorResponse> update(@NonNull final Long id, @NonNull final SensorUpdateRequest request) {
    return ResponseEntity.ok(sensorService.update(id, request));
  }

  @Override
  public ResponseEntity<Void> delete(@NonNull final Long id) {
    sensorService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
