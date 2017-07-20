package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.operation.flight.FlightRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRecordRepository extends JpaRepository<FlightRecord, Integer> {

    //Optional<FlightRecord> findById(Integer id);

}
