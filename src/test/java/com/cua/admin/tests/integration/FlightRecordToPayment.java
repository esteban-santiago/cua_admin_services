package com.cua.admin.tests.integration;

import com.cua.admin.model.operation.flight.CrewMember;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.operation.flight.AircraftService;
import com.cua.admin.services.operation.flight.FlightRecordService;
import com.cua.admin.tests.model.core.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class FlightRecordToPayment extends SpringIntegrationTest {
    @Autowired
    private FlightRecordService flightService;
    @Autowired
    private PersonService personService;
    @Autowired
    private AircraftService aircraftService;
    
    private final FlightRecord record = new FlightRecord();
        
    @Before
    public void createBasicDocuments() throws Throwable {

        record.setCrew(new HashSet<>());

        CrewMember crew = new CrewMember();
        crew.setCrewMemberRole(CrewMemberRole.PIC);
        crew.setPerson(personService.get(100));
        
        record.addCrewMember(crew);

        record.setAircraft(aircraftService.get(100));
        record.setStartFlight(LocalDateTime.now());
        record.setEndFlight(LocalDateTime.now().plusMinutes(70));

        record.close();
        
        flightService.save(record);    
    }
    
    //@Test
    public void paidWithCash() {
        
    }
    
    //@Test
    public void paidWithCreditCard() {
        
    }
    
    @After
    public void list() {
        flightService.getAll().forEach((flightRecord) -> {
            System.out.println(flightRecord);
        });
    }
}
