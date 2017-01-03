package com.cua.admin.repositories.flight;

import com.cua.admin.model.core.flight.Aircraft;
import com.cua.admin.model.core.flight.Crew;
import com.cua.admin.model.core.flight.PilotRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Integer> {

    List<Aircraft> findByPilotRole(PilotRole pilotRole);

}
