package com.cua.admin.tests.model.operation.flight;

import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.model.operation.flight.AircraftComponent;
import com.cua.admin.repositories.operation.flight.AircraftComponentRepository;
import com.cua.admin.services.operation.flight.AircraftService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AircraftTests extends SpringIntegrationTest {

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AircraftComponentRepository aircraftComponentRepository;
      
    @Test
    public void checkActiveInsurancePolicy() {        
        Aircraft c152 = aircraftService.getByRegistration("LV-OEE");
        System.out.println(c152);
        assertThat(c152.hasAnInsurancePolicyInForce())
                .describedAs("No Tiene póliza activa")
                .isTrue();

        assertThat(c152.hasAnInsurancePolicyInForce(LocalDate.now().plusMonths(1)))
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
        Aircraft c152 = aircraftService.getByRegistration("LV-OEE");
        Aircraft c152_2 = aircraftService.getByRegistration("LV-AMS");
        AircraftComponent component = aircraftComponentRepository.findBySerial("1MOTOR100HP").get();
        System.out.println(c152);
        System.out.println(c152_2);
        //--Trasfer engine
        c152_2.getComponents().add(component);
        c152.getComponents().remove(component);
        aircraftService.save(c152);
        aircraftService.save(c152_2);
        System.out.println(c152);
        System.out.println(c152_2);
        
    }
}
