package com.cua.admin.services.operation.flight;

import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.model.operation.flight.exceptions.FlightRecordNotFoundException;
import com.cua.admin.repositories.operation.flight.FlightRecordRepository;
import com.cua.admin.services.finance.FinanceService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightRecordService {

    @Autowired
    private final FlightRecordRepository flightRecordRepository;

    //Servicio de movimientos financieros para grabar en la cuenta corriente
    private final FinanceService financeService;

    public FlightRecord get(Integer id) throws Throwable {
        return flightRecordRepository.findById(id).orElseThrow(
                () -> new FlightRecordNotFoundException(id));
    }

    public List<FlightRecord> getAll() {
        return this.flightRecordRepository.findAll();
    }

    public void delete(Integer id) throws Throwable {
        flightRecordRepository.delete(id);
    }

    public void delete(FlightRecord flightRecord) throws Throwable {
        this.delete(flightRecord.getId());
    }

    public void save(FlightRecord flightRecord) throws Throwable {
        if (flightRecord.isClosed()) {
            this.close(flightRecord);
        } else if (flightRecord.isCanceled()) {
            this.cancel(flightRecord);
        } else {
            flightRecord.setStatus(FlightRecord.Status.OPENED);
            flightRecordRepository.save(flightRecord);
        }
    }

    public void cancel(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CANCELED);
        flightRecordRepository.save(flightRecord);
    }

    public void close(FlightRecord flightRecord) throws Throwable {
        flightRecord.setStatus(FlightRecord.Status.CLOSED);
        flightRecordRepository.save(flightRecord);
        financeService.saveDocument(flightRecord);
    }
}
