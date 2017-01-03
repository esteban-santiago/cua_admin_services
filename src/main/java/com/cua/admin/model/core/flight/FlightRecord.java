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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity
@Table(name = "flight_record")
public class FlightRecord implements Serializable {

    @Id
    private Integer id;
    private Set<CrewMember> crew = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;
    private LocalDateTime startFlight;
    private LocalDateTime endFlight;
    private Integer landings;
    private FlightPurpose purpose;//Finalidad 
    private FlightNature nature;//Carácter
    private FlightType type;//Tipo de vuelo
    @OneToOne
    @JoinColumn(name = "airfield_origin_id")
    private Airfield origin;
    @OneToOne
    @JoinColumn(name = "airfield_destiny_id")
    private Airfield destiny;
    private Float amountOfTime; 
    private String status;
    
    public void addCrewMember(CrewMember crewMember) {
        this.crew.add(crewMember);
    }

    public void removeCrewMember(CrewMember crewMember) {
        this.crew.remove(crewMember);
    }

}
