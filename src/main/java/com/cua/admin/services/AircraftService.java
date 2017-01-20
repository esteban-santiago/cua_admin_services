package com.cua.admin.services;

import com.cua.admin.model.flight.Aircraft;
import com.cua.admin.repositories.flight.AircraftRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AircraftService {
    @Autowired //No es obligatorio
    private final AircraftRepository aircraftRepository;
    
    public Aircraft getAircraft(Integer id) {
        return aircraftRepository.findById(id).get();
    }

    public Aircraft getAircraft(Aircraft aircraft) {
        return getAircraft(aircraft.getId());
    }

    public Aircraft getAircraftByRegistration(String registration) {
        return aircraftRepository.findByRegistration(registration).get();
    }
    
    public List<Aircraft> getAllAircrafts() {
        return this.aircraftRepository.findAll();
    }

    public void saveAircraft(Aircraft aircraft) {
        this.aircraftRepository.save(aircraft);
    }
    
    public Set<Aircraft> checkAircraftWithoutActiveInsurancePolicy(LocalDate atDate) {
        return aircraftRepository.findAll().stream(
        ).filter(aircraft -> !aircraft.hasAnInsurancePolicyInForce(atDate)
        ).map(aircraft -> aircraft
        ).collect(Collectors.toSet());
    }

    public Set<Aircraft> checkAircraftWithoutActiveInsurancePolicy() {
        return aircraftRepository.findAll().stream(
        ).filter(aircraft -> !aircraft.hasAnInsurancePolicyInForce()
        ).map(aircraft -> aircraft
        ).collect(Collectors.toSet());
    }
} 
