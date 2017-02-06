package com.cua.admin.controllers.rest.operation;

import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.services.operation.flight.AircraftService;
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
public class AircraftRestController {

    @Autowired
    AircraftService aircraftService;
    
    @RequestMapping(value = "/aircraft/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Aircraft> get(@RequestParam(value = "id", required = true) Integer id) {
        return new ResponseEntity<>(aircraftService.get(id), HttpStatus.OK);
    }
    
    
}
