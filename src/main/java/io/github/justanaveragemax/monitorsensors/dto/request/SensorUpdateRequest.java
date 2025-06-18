package io.github.justanaveragemax.monitorsensors.dto.request;

import io.github.justanaveragemax.monitorsensors.dto.resposnse.RangeDto;
import io.github.justanaveragemax.monitorsensors.util.DictionaryType;
import io.github.justanaveragemax.monitorsensors.validation.ValidDictionaryValue;
import io.github.justanaveragemax.monitorsensors.validation.ValidRange;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorUpdateRequest {

  @Size(min = 3, max = 30, message = "Name should 3-30 characters in length")
  @Schema(description = "Sensor name", example = "Thermometer")
  private String name;

  @Size(max = 15, message = "Model name should be longer than 15 characters")
  @Schema(description = "Sensor model", example = "ac-23")
  private String model;

  @Schema(description = "Range of measurable values for the sensor")
  @Valid
  @ValidRange
  private RangeDto range;

  @Schema(description = "Sensor type", example = "Temperature")
  @ValidDictionaryValue(dictionary = DictionaryType.SENSOR_TYPE)
  private String type;

  @Schema(description = "Sensor unit", example = "°С")
  @ValidDictionaryValue(dictionary = DictionaryType.SENSOR_UNIT)
  private String unit;

  @Schema(description = "Sensor location", example = "Kitchen")
  @Size(max = 40, message = "Location should be less than 40 characters")
  private String location;

  @Schema(description = "Sensor description", example = "Measures temperature in the room")
  @Size(max = 200, message = "Description should be less than 200 characters")
  private String description;

}
