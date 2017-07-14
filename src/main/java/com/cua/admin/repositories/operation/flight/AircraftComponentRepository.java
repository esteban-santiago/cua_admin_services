package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.operation.flight.AircraftComponent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AircraftComponentRepository extends JpaRepository<AircraftComponent, Integer> {

    Optional<AircraftComponent> findBySerial(String serial);

}
