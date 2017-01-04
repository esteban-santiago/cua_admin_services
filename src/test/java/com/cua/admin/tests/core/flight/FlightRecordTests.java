package com.cua.admin.tests.core.flight;

import com.cua.admin.model.core.flight.CrewMember;
import com.cua.admin.model.core.flight.CrewMemberRole;
import com.cua.admin.model.core.flight.FlightRecord;
import com.cua.admin.model.core.repositories.MemberRepository;
import com.cua.admin.repositories.flight.AircraftRepository;
import com.cua.admin.repositories.flight.CrewRepository;
import com.cua.admin.repositories.flight.FlightRecordRepository;
import com.cua.admin.tests.core.SpringIntegrationTest;
import java.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class FlightRecordTests extends SpringIntegrationTest {

    @Autowired
    private AircraftRepository aircraftRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private FlightRecordRepository flightRecordRepository;
    
    @Test
    public void createFlightRecord() {
        CrewMember crew = new CrewMember();
        crew.setCrewMemberRole(CrewMemberRole.PIC);
        crew.setMember(memberRepository.findById(1));
        
        FlightRecord record = new FlightRecord();
        record.addCrewMember(crew);
        record.setAircraft(aircraftRepository.findById(1));
        record.setStartFlight(LocalDateTime.now());
        record.setEndFlight(LocalDateTime.now().plusMinutes(70));
        System.out.println(record.getAmountOfHours());

        flightRecordRepository.save(record);
        crewRepository.save(crew);
    }

}
