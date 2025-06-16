package io.github.justanaveragemax.monitorsensors.dto.resposnse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Model representing single sensor detailed information")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorResponse {

  @Schema(description = "Unique identifier of the sensor")
  private Long id;

  @Schema(description = "Sensor name", example = "Barometer")
  private String name;

  @Schema(description = "Sensor model", example = "ac-23")
  private String model;

  @Schema(description = "Range of measurable values for the sensor")
  private RangeDto range;

  @Schema(description = "Sensor type", example = "Temperature")
  private String type;

  @Schema(description = "Sensor unit", example = "°С")
  private String unit;

  @Schema(description = "Sensor location", example = "Kitchen")
  private String location;

  @Schema(description = "Sensor description", example = "Measures temperature in the room")
  private String description;

}
