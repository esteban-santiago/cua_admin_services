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
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Data
@Entity
@Table(name = "flight_record")
public class FlightRecord implements Serializable {

    @Id
    private Integer id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_record_id", nullable = false, foreignKey = @ForeignKey(name = "crew_member_id_fk"))
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
    //private Float amountOfTime; 
    private String status;
    
    public void addCrewMember(CrewMember crewMember) {
        this.crew.add(crewMember);
    }

    public void removeCrewMember(CrewMember crewMember) {
        this.crew.remove(crewMember);
    }

    public Float getAmountOfHours() {
        long minutes = ChronoUnit.MINUTES.between(startFlight, endFlight);
        float amountOfHours = (int) (minutes / 60);
        int rest = (int) minutes - ((int)amountOfHours * 60);
        float final_result = (float) (((rest+(rest/8.26)+5)/7)/10);
        float first = (float) (final_result * 100);
        float second = ((int)first /100);
        //float rest2 = ((((long) final_result * 100))/100);
        amountOfHours = amountOfHours + second;
        return amountOfHours;
    }
    
}
