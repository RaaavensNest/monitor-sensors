package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface SensorService {

  PagedModel<SensorResponse> findAll(Pageable pageable);

  SensorResponse create(SensorCreationRequest request);

}
