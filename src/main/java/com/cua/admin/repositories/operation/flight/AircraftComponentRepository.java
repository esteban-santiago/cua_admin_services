package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.operation.flight.AircraftComponent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AircraftComponentRepository extends JpaRepository<AircraftComponent, Integer> {

    AircraftComponent findBySerial(String serial);

}
