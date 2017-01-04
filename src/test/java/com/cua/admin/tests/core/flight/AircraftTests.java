package com.cua.admin.tests.core.flight;

import com.cua.admin.model.core.flight.Aircraft;
import com.cua.admin.model.core.flight.AircraftInsurance;
import com.cua.admin.model.core.flight.CrewMember;
import com.cua.admin.model.core.flight.CrewMemberRole;
import com.cua.admin.model.core.repositories.MemberRepository;
import com.cua.admin.repositories.flight.AircraftRepository;
import com.cua.admin.repositories.flight.CrewRepository;
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
      
    @Test
    public void checkActiveInsurancePolicy() {        
        Aircraft c150 = aircraftRepository.findByRegistration("LV-OEE").get(0);

        assertThat(c150.hasAnInsurancePolicyInForce())
                .describedAs("No Tiene póliza activa")
                .isTrue();

        assertThat(c150.hasAnInsurancePolicyInForce(LocalDate.now().plusMonths(5)))
                .describedAs("True erroneo")
                .isTrue();
    }

    @Test
    public void checkAircraftWithoutActiveInsurancePolicy() {
        aircraftRepository.findAll().stream().filter(
                    aircraft -> !aircraft.hasAnInsurancePolicyInForce()
        ).forEach(aircraft -> System.out.println(aircraft.getRegistration()));
    }        

}
