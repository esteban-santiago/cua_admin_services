package com.cua.admin.repositories.core.flight;

import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.flight.CrewMember;
import com.cua.admin.model.core.flight.CrewMemberRole;
import com.cua.admin.model.core.flight.FlightRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRecordRepository extends JpaRepository<FlightRecord, Long> {
  
}
