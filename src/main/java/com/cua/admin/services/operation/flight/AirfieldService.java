package com.cua.admin.services.operation.flight;

import com.cua.admin.model.operation.flight.Airfield;
import com.cua.admin.repositories.operation.flight.AirfieldRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AirfieldService {
    @Autowired //No es obligatorio
    private final AirfieldRepository airfieldRepository;

    
    public Airfield get(Integer id) {
        return airfieldRepository.findById(id).get();
    }

    public Airfield get(Airfield airfield) {
        return get(airfield.getId());
    }

    public List<Airfield> getAll() {
        return this.airfieldRepository.findAll();
    }

    public void save(Airfield airfield) {
        this.airfieldRepository.save(airfield);
    }
} 
