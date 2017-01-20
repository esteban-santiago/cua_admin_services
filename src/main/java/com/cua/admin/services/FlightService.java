package com.cua.admin.services;

import com.cua.admin.model.flight.FlightRecord;
import com.cua.admin.repositories.flight.AircraftRepository;
import com.cua.admin.repositories.flight.CrewRepository;
import com.cua.admin.repositories.flight.FlightRecordRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightService {
    @Autowired //No es obligatorio
    private final AircraftRepository aircraftRepository;
    
    @Autowired
    private final MemberService memberService;
    
    @Autowired
    private final CrewRepository crewRepository;

    @Autowired
    private final FlightRecordRepository flightRecordRepository;
   
    
    public void createFlightRecord(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.OPENED);
        flightRecordRepository.save(flightRecord);
    }
} 
