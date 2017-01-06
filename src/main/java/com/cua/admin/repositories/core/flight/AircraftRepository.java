package com.cua.admin.repositories.core.flight;

import com.cua.admin.model.core.flight.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

    List<Aircraft> findByRegistration(String description);

    Aircraft findById(Integer id);
}
