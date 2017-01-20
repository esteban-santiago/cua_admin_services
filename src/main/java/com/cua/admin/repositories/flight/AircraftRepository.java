package com.cua.admin.repositories.flight;

import com.cua.admin.model.flight.Aircraft;
import com.cua.admin.model.flight.AircraftComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

    Aircraft findByRegistration(String description);

    Aircraft findById(Integer id);
    
    Aircraft findByComponents(AircraftComponent component);
}
