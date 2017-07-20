package com.cua.admin.controllers.rest.core.building;

import com.cua.admin.model.core.building.Hangar;
import com.cua.admin.model.core.building.Location;
import com.cua.admin.model.core.building.Position;
import com.cua.admin.services.administration.building.LocationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/administration/building")
@RequiredArgsConstructor
public class LocationRestController {

    @Autowired
    private final LocationService locationService;
    
    @RequestMapping(value = "/location", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Location>> get() {
        return ResponseEntity.ok(locationService.getAll());
    }
    
    @RequestMapping(value = "/location", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Location> save(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.save(location));
    }
    
    @RequestMapping(value = "/hangar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Hangar> save(@RequestBody Hangar hangar) {
        return ResponseEntity.ok(locationService.save(hangar));
    }

    @RequestMapping(value = "/hangar/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Hangar> getById(@PathVariable("id")Integer id) {
        return ResponseEntity.ok(locationService.getHangarById(id));
    }

    @RequestMapping(value = "/hangar/{id}/position", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Position>> getPositionsByHangarId(@PathVariable("id")Integer id) {
        return ResponseEntity.ok(locationService.getPositionsByHangar(locationService.getHangarById(id)));
    }

}
