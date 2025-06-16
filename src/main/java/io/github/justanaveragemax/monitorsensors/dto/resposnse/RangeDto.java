package io.github.justanaveragemax.monitorsensors.dto.resposnse;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "Model representing sensor's range of measurement")
@NoArgsConstructor
@AllArgsConstructor
public class RangeDto {

  @NotNull(message = "'from' should not be null")
  @Positive(message = "'from' should be positive")
  @Schema(description = "Minimum measurable value of sensor", example = "22", requiredMode = RequiredMode.REQUIRED)
  private Integer from;

  @NotNull(message = "'from' should not be null")
  @Positive(message = "'from' should be positive")
  @Schema(description = "Maximum measurable value of sensor", example = "45", requiredMode = RequiredMode.REQUIRED)
  private Integer to;

}
