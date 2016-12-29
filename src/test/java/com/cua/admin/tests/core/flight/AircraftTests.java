package com.cua.admin.tests.core.flight;

import com.cua.admin.model.core.flight.Aircraft;
import com.cua.admin.model.core.flight.AircraftInsurance;
import com.cua.admin.repositories.flight.AircraftRepository;
import com.cua.admin.tests.core.SpringIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class AircraftTests extends SpringIntegrationTest {

    @Autowired
    private AircraftRepository aircraftRepository;

    //@Test
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
        c150.setInsurance(Collections.singleton(seguro));
        aircraftRepository.save(c150); 
    }
    
    @Test
    public void checkActiveInsurancePolicy() {
        create150();
        
        Aircraft c150 = aircraftRepository.findByRegistration("LV-OEE").get(0);

        assertThat(c150.hasAnInsurancePolicyInForce())
                .describedAs("No Tiene póliza activa")
                .isTrue();

        assertThat(c150.hasAnInsurancePolicyInForce(LocalDate.now().plusMonths(5)))
                .describedAs("True erroneo")
                .isTrue();
    }

}
