package com.cua.admin.controllers.rest.operation;

import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.services.operation.flight.FlightRecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class FlightRecordRestController {

    @Autowired
    FlightRecordService flightRecordService;
    
    @RequestMapping(value = "/flight_record/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<FlightRecord> get(@RequestParam(value = "id", required = true) Integer id) {
        return new ResponseEntity<>(flightRecordService.getFlightRecord(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/flight_record", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<FlightRecord>> get() {
        return new ResponseEntity<>(flightRecordService.getAllFlightRecord(), HttpStatus.OK);
    }
    
    
}
