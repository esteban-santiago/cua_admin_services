package com.cua.admin.services;

import com.cua.admin.model.core.flight.Aircraft;
import com.cua.admin.repositories.flight.AircraftRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AircraftService {

    private final AircraftRepository aircraftRepository;
        
    public Set<Aircraft> checkAircraftWithoutActiveInsurancePolicy() {
        return aircraftRepository.findAll().stream(
        ).filter(aircraft -> !aircraft.hasAnInsurancePolicyInForce()
        ).map(aircraft -> aircraft
        ).collect(Collectors.toCollection(HashSet::new));
    }
} 
