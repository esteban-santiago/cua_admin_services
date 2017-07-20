package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.operation.flight.Airfield;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirfieldRepository extends JpaRepository<Airfield, Integer> {
    //Optional<Airfield> findById(Integer id);
}
