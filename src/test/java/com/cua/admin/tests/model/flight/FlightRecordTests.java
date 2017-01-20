package com.cua.admin.tests.model.flight;

import com.cua.admin.model.flight.CrewMember;
import com.cua.admin.model.flight.CrewMemberRole;
import com.cua.admin.model.flight.FlightRecord;
import com.cua.admin.repositories.flight.CrewRepository;
import com.cua.admin.services.AircraftService;
import com.cua.admin.services.FlightService;
import com.cua.admin.services.MemberService;
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
        crew.setMember(memberService.getMember(100));
        
        record.addCrewMember(crew);

        record.setAircraft(aircraftService.getAircraft(100));
        record.setStartFlight(LocalDateTime.now());
        record.setEndFlight(LocalDateTime.now().plusMinutes(70));
        System.out.println(record.getAmountOfHours());
        flightService.createFlightRecord(record);
    }

}
