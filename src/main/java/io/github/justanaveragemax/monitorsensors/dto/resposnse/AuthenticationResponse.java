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
public class AuthenticationResponse {

  @Schema(description = "Unique token granted after authentication that is required for accessing protected endpoints")
  private String accessToken;

  @Schema(description = "Unique token granted after authentication that is required for refreshing access token")
  private String refreshToken;

}
