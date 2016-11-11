/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.flight;

import com.cua.admin.model.entities.Member;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author esteban_santiago
 */
@ToString
@EqualsAndHashCode
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
    
    
    public FlightRecord() {
        
    }
}
