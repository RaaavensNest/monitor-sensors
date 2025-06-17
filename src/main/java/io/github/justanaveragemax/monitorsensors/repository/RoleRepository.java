package io.github.justanaveragemax.monitorsensors.repository;

import io.github.justanaveragemax.monitorsensors.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
