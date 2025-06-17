package io.github.justanaveragemax.monitorsensors.dto.resposnse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorCreationResponse {

  @Schema(description = "Unique identifier of created sensor")
  private Long id;

  @Schema(description = "Name of created sensor", example = "Barometer")
  private String name;

  @Schema(description = "Model of created sensor", example = "ac-23")
  private String model;
}
