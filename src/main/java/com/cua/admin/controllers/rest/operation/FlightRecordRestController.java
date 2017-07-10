package com.cua.admin.controllers.rest.operation;

import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.services.operation.flight.FlightRecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sapi/operation/flight_record")
@RequiredArgsConstructor
public class FlightRecordRestController {

    @Autowired
    FlightRecordService flightRecordService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<FlightRecord> get(@PathVariable("id") Integer id) throws Throwable {
        return ResponseEntity.ok(flightRecordService.get(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<FlightRecord>> get() {
        return ResponseEntity.ok(flightRecordService.getAll());
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<FlightRecord> create(@RequestBody FlightRecord flightRecord) throws Throwable {
        flightRecordService.save(flightRecord);
        return new ResponseEntity<>(flightRecord, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<FlightRecord> save(@RequestBody FlightRecord flightRecord) throws Throwable {
        flightRecordService.save(flightRecord);
        return ResponseEntity.ok(flightRecord);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Throwable {
        flightRecordService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "", 
            params = { "page", "size" }, 
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<FlightRecord>> getAllPaginated(
            @RequestParam(value = "page") Integer page, 
            @RequestParam(value = "size") Integer size) {
        return ResponseEntity.ok(flightRecordService.getAllByPage(page, size));
    }   

}
