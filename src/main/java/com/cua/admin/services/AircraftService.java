package com.cua.admin.services;

import com.cua.admin.model.core.flight.Aircraft;
import com.cua.admin.repositories.core.flight.AircraftRepository;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AircraftService {

    private final AircraftRepository aircraftRepository;
    
    public Aircraft getAircraft(Integer id) {
        return aircraftRepository.findById(id);
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
