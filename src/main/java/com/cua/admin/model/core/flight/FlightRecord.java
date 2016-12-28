package com.cua.admin.model.core.flight;

import com.cua.admin.model.core.Member;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "flight_record")
public class FlightRecord implements Serializable {

    @Id
    private Integer id;
    private Member member;
    private Aircraft aircraft;
    private Member instructor;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private Integer landings;
    //finalidad
    //Carácter
    //Tipo de vuelo
    private Airfield origin;
    private Airfield destiny;
    private Float amountOfTime; 
    private String status;
    
}
