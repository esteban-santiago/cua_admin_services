package com.cua.admin.repositories.core.flight;

import com.cua.admin.model.core.flight.AircraftComponent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AircraftComponentRepository extends JpaRepository<AircraftComponent, Integer> {

    AircraftComponent findBySerial(String serial);

}
