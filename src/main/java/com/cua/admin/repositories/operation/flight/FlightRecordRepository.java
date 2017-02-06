package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.core.profiles.Member;
import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.model.operation.flight.CrewMember;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRecordRepository extends JpaRepository<FlightRecord, Integer> {

    Optional<FlightRecord> findById(Integer id);
}
