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
public class RegistrationRequest {

  @NotNull(message = "Field 'firstName' should not be null")
  @NotBlank(message = "Field 'firstName' should not be empty")
  @Schema(description = "User first name", example = "John")
  private String firstName;

  @NotNull(message = "Field 'lastName' should not be null")
  @NotBlank(message = "Field 'lastName' should not be empty")
  @Schema(description = "User last name", example = "Doe")
  private String lastName;

  @NotNull(message = "Field 'email' should not be null")
  @NotBlank(message = "Field 'email' should not be empty")
  @Email
  @Schema(description = "User email", example = "j.doe@example.com")
  private String email;

  @NotNull(message = "Field 'email' should not be null")
  @NotBlank(message = "Field 'email' should not be empty")
  @Size(min = 8, max = 32, message = "Password length should be between 8 and 32 characters")
  @Schema(description = "User password", example = "Password123!")
  private String password;

}
