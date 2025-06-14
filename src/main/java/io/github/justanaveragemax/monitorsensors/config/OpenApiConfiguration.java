package io.github.justanaveragemax.monitorsensors.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Monitor Sensors API",
        version = "1.0.0",
        description = "API for managing sensors",
        contact = @Contact(
            name = "Maksim Krutalevich",
            email = "maximkrutalevich@gmail.com"
        )
    )
)
public class OpenApiConfiguration {

  final static private String BEARER_AUTH = "BearerAuth";

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH))
        .components(new Components()
            .addSecuritySchemes(BEARER_AUTH, new SecurityScheme()
                .name(BEARER_AUTH)
                .type(Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
            )
        );
  }
}
