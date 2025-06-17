package io.github.justanaveragemax.monitorsensors.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  @NotNull(message = "Field 'email' should not be null")
  @NotBlank(message = "Field 'email' should not be empty")
  @Email
  @Schema(description = "Email used during registration", example = "j.doe@example.com")
  private String email;

  @NotNull(message = "Field 'password' should not be null")
  @NotBlank(message = "Field 'password' should not be empty")
  @Size(min = 8, max = 32, message = "Password length should be between 8 and 32 characters")
  @Schema(description = "Password used during registration", example = "Password123!")
  private String password;

}
