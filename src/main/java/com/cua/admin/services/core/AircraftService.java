package com.cua.admin.services.core;

import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.repositories.operation.flight.AircraftRepository;
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

    
    public Aircraft get(Integer id) {
        return aircraftRepository.findById(id).get();
    }

    public Aircraft get(Aircraft aircraft) {
        return get(aircraft.getId());
    }

    public Aircraft getByRegistration(String registration) {
        return aircraftRepository.findByRegistration(registration).get();
    }
    
    public List<Aircraft> getAll() {
        return this.aircraftRepository.findAll();
    }

    public void save(Aircraft aircraft) {
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
