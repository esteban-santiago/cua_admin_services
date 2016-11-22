package com.cua.admin.tests.entities.flight;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.core.flight.Aircraft;
import com.cua.admin.model.core.flight.AircraftInsurance;
import com.cua.admin.repositories.flight.AircraftRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import org.junit.Assert;
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
        c150.setBrand("Cessna");
        c150.setStatus("A");
        AircraftInsurance seguro = new AircraftInsurance();
        seguro.setCompany("Sancor");
        seguro.setPolicy("ABC-4444224422");
        seguro.setType("Terceros Completo");
        seguro.setValidityFrom(LocalDate.now().minus(6, ChronoUnit.MONTHS));
        seguro.setValidityTo(LocalDate.now().plus(8, ChronoUnit.MONTHS));
        c150.setInsurance(seguro);
        aircraftRepository.save(c150); 
    }
    
    @Test
    public void checkActiveInsurancePolicy() {
        Aircraft c150 = aircraftRepository.findByRegistration("LV-OEE").get(0);
        Assert.assertTrue("No Tiene póliza activa", c150.hasAnInsurancePolicyInForce());
        Assert.assertTrue("True erroneo", c150.hasAnInsurancePolicyInForce(LocalDate.now().plus(5, ChronoUnit.MONTHS)));
        //System.out.println(c150);
    }

}
