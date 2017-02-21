package com.cua.admin.controllers.rest.operation;

import com.cua.admin.model.operation.flight.FlightNature;
import com.cua.admin.model.operation.flight.FlightPurpose;
import com.cua.admin.model.operation.flight.FlightType;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class FlightRestController {
    
    @RequestMapping(value = "/flight/nature", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getFlightNature() {
        Map ratings = new HashMap<>();
        String values[];
        for(int i = 0 ; i < FlightNature.values().length; i = i + 1) {
            values = new String[2];
            values[0] = FlightNature.values()[i].name();
            values[1] = FlightNature.values()[i].getDescription();
            ratings.put(i, values);
        }
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @RequestMapping(value = "/flight/purpose", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getFlightPurpose() {
        Map ratings = new HashMap<>();
        String values[];
        for(int i = 0 ; i < FlightPurpose.values().length; i = i + 1) {
            values = new String[2];
            values[0] = FlightPurpose.values()[i].name();
            values[1] = FlightPurpose.values()[i].getDescription();
            ratings.put(i, values);
        }
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @RequestMapping(value = "/flight/type", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getFlightType() {
        Map ratings = new HashMap<>();
        String values[];
        for(int i = 0 ; i < FlightType.values().length; i = i + 1) {
            values = new String[2];
            values[0] = FlightType.values()[i].name();
            values[1] = FlightType.values()[i].getDescription();
            ratings.put(i, values);
        }
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
    
}

