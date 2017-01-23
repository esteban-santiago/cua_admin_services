package com.cua.admin.controllers.rest.operation;

import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.services.core.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class FlightRestController {

    @Autowired
    AircraftService aircraftService;
    
    @RequestMapping(value = "/aircraft", method = RequestMethod.GET, headers = "Accept=application/json")
    public Aircraft getAircraft(@RequestParam(value = "id", required = true) Integer id) {
        return aircraftService.get(id);
    }
}
