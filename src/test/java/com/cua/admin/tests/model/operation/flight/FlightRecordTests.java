package com.cua.admin.tests.model.operation.flight;

import com.cua.admin.model.operation.flight.CrewMember;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.repositories.operation.flight.CrewRepository;
import com.cua.admin.services.operation.flight.AircraftService;
import com.cua.admin.services.operation.flight.FlightService;
import com.cua.admin.services.core.MemberService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import java.time.LocalDateTime;
import java.util.HashSet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class FlightRecordTests extends SpringIntegrationTest {

    @Autowired
    private AircraftService aircraftService;
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private FlightService flightService;
    
    @Test
    public void createFlightRecord() throws Throwable {
        FlightRecord record = new FlightRecord();
        record.setCrew(new HashSet<>());

        CrewMember crew = new CrewMember();
        crew.setCrewMemberRole(CrewMemberRole.PIC);
        crew.setMember(memberService.get(100));
        
        record.addCrewMember(crew);

        record.setAircraft(aircraftService.get(100));
        record.setStartFlight(LocalDateTime.now());
        record.setEndFlight(LocalDateTime.now().plusMinutes(70));
        System.out.println(record.getAmountOfHours());
        flightService.createFlightRecord(record);
    }

}
