package io.github.justanaveragemax.monitorsensors.util.search;

import io.github.justanaveragemax.monitorsensors.entity.Sensor;
import jakarta.persistence.criteria.Expression;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

public class SensorSpecifications {

  public static Specification<Sensor> containsIgnoreCase(@NonNull final SearchableSensorField field,
                                                         @NonNull final String value) {
    return (root, query, cb) -> {
      if (value.isBlank()) {
        return cb.conjunction();
      }

      final Expression<String> expression = cb.lower(root.get(field.getValue()));
      final String pattern = String.format("%%%s%%", value.toLowerCase());
      return cb.like(expression, pattern);
    };
  }

}
