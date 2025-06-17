package io.github.justanaveragemax.monitorsensors.controller;

import io.github.justanaveragemax.monitorsensors.dto.request.SensorCreationRequest;
import io.github.justanaveragemax.monitorsensors.dto.request.SensorUpdateRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorCreationResponse;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.SensorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sensors")
@Tag(name = "Sensors", description = "Endpoints for sensors management")
public interface SensorApi {

  @GetMapping
  @PageableAsQueryParam
  @Operation(
      summary = "Get paginated list of sensors",
      description = "Return a page of sensors based on the provided pagination parameters"
  )
  ResponseEntity<PagedModel<SensorResponse>> findAll(@Parameter(hidden = true) Pageable pageable);

  @GetMapping("/{id}")
  @Operation(
      summary = "Get sensor information by ID",
      description = "Return detailed information of a sensor based on provided id parameter"
  )
  ResponseEntity<SensorResponse> findById(@PathVariable("id") Long id);

  @PostMapping
  @Operation(
      summary = "Create new sensor",
      description = "Create new sensor based on provided parameters and saves it in database"
  )
  ResponseEntity<SensorCreationResponse> create(@RequestBody @Valid SensorCreationRequest request);


  @PatchMapping("/{id}")
  @Operation(
      summary = "Update sensor",
      description = "Update sensor fields by provided id"
  )
  ResponseEntity<SensorResponse> update(@PathVariable("id") Long id, @RequestBody @Valid SensorUpdateRequest request);

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Delete sensor",
      description = "Delete sensor based on provided id"
  )
  ResponseEntity<Void> delete(@PathVariable("id") Long id);

}
