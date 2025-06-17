package io.github.justanaveragemax.monitorsensors.repository;

import io.github.justanaveragemax.monitorsensors.entity.SensorUnit;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorUnitRepository extends JpaRepository<SensorUnit, Long> {

  Optional<SensorUnit> findByName(String name);

  boolean existsByNameIgnoreCase(String name);

}
