package com.cua.admin.controllers.rest.operation;

import com.cua.admin.model.operation.flight.Airfield;
import com.cua.admin.services.operation.flight.AirfieldService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/operation")
@RequiredArgsConstructor
public class AirfieldRestController {

    @Autowired
    AirfieldService airfieldService;
    
    @RequestMapping(value = "/airfield/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Airfield> get(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(airfieldService.get(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/airfield", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Airfield>> get() {
        return new ResponseEntity<>(airfieldService.getAll(), HttpStatus.OK);
    }    
}
