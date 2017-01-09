package com.cua.admin.model.core.flight;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "flight_record")
public class FlightRecord implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "flight_record_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_record_id", nullable = false, foreignKey = @ForeignKey(name = "flight_record_member_id_fk"))
    private Set<CrewMember> crew = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "aircraft_id", nullable = false, foreignKey = @ForeignKey(name = "flight_record_aircraft_id_fk"))
    private Aircraft aircraft;
    private LocalDateTime startFlight = LocalDateTime.now();
    private LocalDateTime endFlight = LocalDateTime.now();
    private Integer landings = 0;
    
    @Enumerated(EnumType.STRING)
    private FlightPurpose purpose = FlightPurpose.VP;//Finalidad 
    
    @Enumerated(EnumType.STRING)
    private FlightNature nature = FlightNature.LDI;//Carácter
    
    @Enumerated(EnumType.STRING)
    private FlightType type = FlightType.ENT;//Tipo de vuelo
    
    @OneToOne
    @JoinColumn(name = "airfield_origin_id", nullable = true, foreignKey = @ForeignKey(name = "flight_record_airfield_origin_id_fk"))
    private Airfield origin;
    @OneToOne
    @JoinColumn(name = "airfield_destiny_id", nullable = true, foreignKey = @ForeignKey(name = "flight_record_airfield_destiny_id_fk"))
    private Airfield destiny;
    
    //private Float amountOfTime; 
    @Enumerated(EnumType.STRING)
    private FlightRecordStatus status = FlightRecordStatus.OPENED;
    
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
        //float first = (int) (final_result * 10);
        float second = ((int) (final_result * 10)) /10;
        //float rest2 = ((((long) final_result * 100))/100);
        amountOfHours = amountOfHours + second;
        return amountOfHours;
    }
    
}
