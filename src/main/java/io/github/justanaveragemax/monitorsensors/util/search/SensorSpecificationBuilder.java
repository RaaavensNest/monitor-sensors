package io.github.justanaveragemax.monitorsensors.util.search;

import io.github.justanaveragemax.monitorsensors.entity.Sensor;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

public class SensorSpecificationBuilder {

  public static Specification<Sensor> build(@NonNull final SensorSearchCriteria criteria) {

    List<Specification<Sensor>> specs = new ArrayList<>();

    if (criteria.name() != null && !criteria.name().isBlank()) {
      specs.add(SensorSpecifications.containsIgnoreCase(SearchableSensorField.NAME, criteria.name()));
    }
    if (criteria.model() != null && !criteria.model().isBlank()) {
      specs.add(SensorSpecifications.containsIgnoreCase(SearchableSensorField.MODEL, criteria.model()));
    }

    return Specification.allOf(specs);
  }

}
