package io.github.justanaveragemax.monitorsensors.repository;

import io.github.justanaveragemax.monitorsensors.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>, JpaSpecificationExecutor<Sensor> {

}
