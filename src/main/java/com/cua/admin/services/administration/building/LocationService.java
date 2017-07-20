package com.cua.admin.services.administration.building;

import com.cua.admin.model.core.building.Hangar;
import com.cua.admin.model.core.building.Location;
import com.cua.admin.model.core.building.Position;
import com.cua.admin.repositories.core.building.HangarRepository;
import com.cua.admin.repositories.core.building.LocationRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cua.admin.repositories.core.building.PositionRepository;

/**
 *
 * @author esantiago
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    @Autowired
    private final HangarRepository hangarRepository;

    @Autowired
    private final PositionRepository positionRepository;

    @Autowired
    private final LocationRepository locationRepository;

    /*
    ** Location Basic Services
    */
    public <T extends Location> T get(Integer id) {
        return (T) this.locationRepository.findById(id).get();
    }
    
    public <T extends Location> T save(T location) {
        return (T) this.locationRepository.save(location);
    }
    
    public List<Location> getAll() {
        return this.locationRepository.findAll();
    }
        
    public Hangar getHangarById(Integer id) {
        return (Hangar) this.locationRepository.findHangarById(id).get();
    }

    
    public List<Position> getPositionsByHangar(Hangar hangar) {
        return this.locationRepository.findByHangar(hangar);
    }
    
}
