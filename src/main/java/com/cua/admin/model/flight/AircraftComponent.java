package com.cua.admin.model.flight;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@Table(name = "aircraft_component", uniqueConstraints = {@UniqueConstraint(columnNames = {"serial"})})
public class AircraftComponent implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "aircraft_component_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private String brand; //Marca
    
    private String description;
    
    private String serial;    
    
    private Boolean relocable;
    
    @Enumerated(EnumType.STRING)
    private Type type;
    
    public enum Type {
        ENGINE(),        //Motor
        PROPELLER(),    //Hélice
        CAPSULE();      //Capsula
    }
    
}
