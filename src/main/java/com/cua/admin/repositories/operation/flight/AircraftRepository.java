package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.model.operation.flight.AircraftComponent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

    Optional<Aircraft> findByRegistration(String description);

    //Optional<Aircraft> findById(Integer id);
    
    Optional<Aircraft> findByComponents(AircraftComponent component);
}
