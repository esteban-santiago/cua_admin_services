package com.cua.admin.services.flight;

import com.cua.admin.services.core.MemberService;
import com.cua.admin.model.flight.FlightRecord;
import com.cua.admin.repositories.flight.AircraftRepository;
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
    private final FlightRecordRepository flightRecordRepository;
   
   
    public void createFlightRecord(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.PENDING);
        flightRecordRepository.save(flightRecord);
    }

    public void cancelFlightRecord(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CANCELED);
        flightRecordRepository.save(flightRecord);
    }    
    
    public void saveFlightRecord(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CLOSED);
        flightRecordRepository.save(flightRecord);
    }
} 