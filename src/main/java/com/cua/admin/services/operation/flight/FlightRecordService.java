package com.cua.admin.services.operation.flight;

import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.model.operation.flight.exceptions.FlightRecordNotFoundException;
import com.cua.admin.repositories.operation.flight.AircraftRepository;
import com.cua.admin.repositories.operation.flight.FlightRecordRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightRecordService {
    @Autowired //No es obligatorio
    private final AircraftRepository aircraftRepository;
    
    @Autowired
    private final FlightRecordRepository flightRecordRepository;
   
    public FlightRecord getFlightRecord(Integer id) throws Throwable {
        return flightRecordRepository.findById(id).orElseThrow(
        () -> new FlightRecordNotFoundException(id));
    }

    public List<FlightRecord> getAllFlightRecord() {
        return this.flightRecordRepository.findAll();
    }
    
    public void create(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.OPENED);
        flightRecordRepository.save(flightRecord);
    }

    public void cancel(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CANCELED);
        flightRecordRepository.save(flightRecord);
    }    
    
    public void close(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CLOSED);
        flightRecordRepository.save(flightRecord);
    }
} 
