package com.cua.admin.tests.model.flight;

import com.cua.admin.model.flight.Aircraft;
import com.cua.admin.model.flight.AircraftComponent;
import com.cua.admin.repositories.flight.AircraftComponentRepository;
import com.cua.admin.services.AircraftService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AircraftTests extends SpringIntegrationTest {

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AircraftComponentRepository aircraftComponentRepository;
      
    @Test
    public void checkActiveInsurancePolicy() {        
        Aircraft c152 = aircraftService.getAircraftByRegistration("LV-OEE");
        System.out.println(c152);
        assertThat(c152.hasAnInsurancePolicyInForce())
                .describedAs("No Tiene póliza activa")
                .isTrue();

        assertThat(c152.hasAnInsurancePolicyInForce(LocalDate.now().plusMonths(5)))
                .describedAs("True erroneo")
                .isTrue();
    }

    @Test
    public void checkAircraftWithoutActiveInsurancePolicy() {
        aircraftService.getAll().stream().filter(
                    aircraft -> !aircraft.hasAnInsurancePolicyInForce()
        ).forEach(aircraft -> System.out.println(aircraft.getRegistration()));
        assertThat( aircraftService.getAll().stream().filter(
                    aircraft -> !aircraft.hasAnInsurancePolicyInForce()
        )).describedAs("Todos tienen póliza");
    }
    
    @Test
    public void transferComponent() {        
        Aircraft c152 = aircraftService.getAircraftByRegistration("LV-OEE");
        Aircraft c152_2 = aircraftService.getAircraftByRegistration("LV-AMS");
        AircraftComponent component = aircraftComponentRepository.findBySerial("1MOTOR100HP");
        System.out.println(c152);
        System.out.println(c152_2);
        //--Trasfer engine
        c152_2.getComponents().add(component);
        c152.getComponents().remove(component);
        aircraftService.saveAircraft(c152);
        aircraftService.saveAircraft(c152_2);
        System.out.println(c152);
        System.out.println(c152_2);
        
    }
}
