package com.cua.admin.tests.model.operation.flight;

import com.cua.admin.model.operation.flight.CrewMember;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.repositories.operation.flight.CrewRepository;
import com.cua.admin.services.operation.flight.AircraftService;
import com.cua.admin.services.operation.flight.FlightRecordService;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import java.time.LocalDateTime;
import java.util.HashSet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class FlightRecordTests extends SpringIntegrationTest {

    @Autowired
    private AircraftService aircraftService;
    
    @Autowired
    private PersonService personService;

    @Autowired
    private FlightRecordService flightService;
    
    @Test
    public void createFlightRecord() throws Throwable {
        /*
        FlightRecord record = new FlightRecord();
        record.setCrew(new HashSet<>());

        CrewMember crew = new CrewMember();
        crew.setCrewMemberRole(CrewMemberRole.PIC);
        crew.setPerson(personService.get(100));
        
        record.addCrewMember(crew);

        record.setAircraft(aircraftService.get(100));
        record.setStartFlight(LocalDateTime.now());
        record.setEndFlight(LocalDateTime.now().plusMinutes(70));
        System.out.println(record.getAmountOfHours());
        flightService.createFlightRecord(record);
        */
    }

}
