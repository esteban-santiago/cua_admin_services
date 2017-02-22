package com.cua.admin.services.operation.flight;

import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.model.operation.flight.exceptions.FlightRecordNotFoundException;
import com.cua.admin.repositories.operation.flight.AircraftRepository;
import com.cua.admin.repositories.operation.flight.FlightRecordRepository;
import com.cua.admin.services.finance.DocumentService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightRecordService {
    
    //Servicio de documentos financieros para grabar en la cuenta corriente
    private final DocumentService documentService;
    
    @Autowired
    private final FlightRecordRepository flightRecordRepository;
   
    public FlightRecord getFlightRecord(Integer id) throws Throwable {
        return flightRecordRepository.findById(id).orElseThrow(
        () -> new FlightRecordNotFoundException(id));
    }

    public List<FlightRecord> getAllFlightRecord() {
        return this.flightRecordRepository.findAll();
    }
    
    public void deleteFlightRecord(Integer id) throws Throwable {
        flightRecordRepository.delete(id);
    }

    public void deleteFlightRecord(FlightRecord flightRecord) throws Throwable {
        this.deleteFlightRecord(flightRecord.getId());
    }

    public void saveFlightRecord(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.OPENED);
        flightRecordRepository.save(flightRecord);
    }

    public void cancelFlightRecord(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CANCELED);
        flightRecordRepository.save(flightRecord);
    }    
    
    public void closeFlightRecord(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CLOSED);
        flightRecordRepository.save(flightRecord);
        documentService.saveDocument(flightRecord);
    }
} 
