package com.cua.admin.controllers.rest.operation;

import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.services.operation.flight.FlightRecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class FlightRecordRestController {

    @Autowired
    FlightRecordService flightRecordService;

    @RequestMapping(value = "/flight_record/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<FlightRecord> get(@PathVariable("id") Integer id) throws Throwable {
        return new ResponseEntity<>(flightRecordService.getFlightRecord(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/flight_record", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<FlightRecord>> get() {
        return new ResponseEntity<>(flightRecordService.getAllFlightRecord(), HttpStatus.OK);
    }

    @RequestMapping(value = "/flight_record", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<FlightRecord> create(@RequestBody FlightRecord flightRecord) throws Throwable {
        if (flightRecord.isOpened()) {
            flightRecordService.saveFlightRecord(flightRecord);
        } else if (flightRecord.isClosed()) {
            flightRecordService.closeFlightRecord(flightRecord);
        }

        return new ResponseEntity<>(flightRecord, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/flight_record/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Throwable {
        flightRecordService.deleteFlightRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
