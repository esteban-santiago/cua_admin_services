package com.cua.admin.tests.entities.flight;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.flight.Aircraft;
import com.cua.admin.model.flight.AircraftInsurance;
import com.cua.admin.repositories.flight.AircraftRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AircraftTests {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Test
    public void create150() {
        Aircraft c150 = new Aircraft();
        c150.setBrand("Cessna");
        c150.setModel("150");
        c150.setRegistration("LV-OEE");
        aircraftRepository.save(c150);
        AircraftInsurance seguro = new AircraftInsurance();
        
        
    }

}
