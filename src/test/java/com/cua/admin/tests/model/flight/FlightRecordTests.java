package com.cua.admin.tests.model.flight;

import com.cua.admin.model.flight.CrewMember;
import com.cua.admin.model.flight.CrewMemberRole;
import com.cua.admin.model.flight.FlightRecord;
import com.cua.admin.repositories.core.MemberRepository;
import com.cua.admin.repositories.flight.AircraftRepository;
import com.cua.admin.repositories.flight.CrewRepository;
import com.cua.admin.repositories.flight.FlightRecordRepository;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
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
        crew.setMember(memberRepository.findById(100));
        
        FlightRecord record = new FlightRecord();
        record.addCrewMember(crew);
        record.setAircraft(aircraftRepository.findById(100));
        record.setStartFlight(LocalDateTime.now());
        record.setEndFlight(LocalDateTime.now().plusMinutes(70));
        System.out.println(record.getAmountOfHours());
        record.setStatus(FlightRecord.Status.OPENED);
        flightRecordRepository.save(record);
        System.out.println(record);
        System.out.println(record.getCrew());
    }

}
