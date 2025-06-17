package io.github.justanaveragemax.monitorsensors.repository;

import io.github.justanaveragemax.monitorsensors.entity.SensorType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {

  Optional<SensorType> findByName(String name);

}
