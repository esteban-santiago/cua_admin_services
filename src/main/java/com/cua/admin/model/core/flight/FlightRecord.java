package com.cua.admin.model.core.flight;

import com.cua.admin.model.core.Member;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "flight_record")
public class FlightRecord implements Serializable {

    @Id
    private Integer id;
    private Set<CrewMember> crew = new HashSet<>();
    private Aircraft aircraft;
    private Member instructor;
    private LocalDateTime start;
    private LocalDateTime end;
    private Integer landings;
    private FlightPurpose purpose;//Finalidad 
    private FlightNature nature;//Carácter
    private FlightType type;//Tipo de vuelo
    private Airfield origin;
    private Airfield destiny;
    private Float amountOfTime; 
    private String status;
    
    public void addCrewMember(CrewMember crewMember) {
        this.crew.add(crewMember);
    }
}
