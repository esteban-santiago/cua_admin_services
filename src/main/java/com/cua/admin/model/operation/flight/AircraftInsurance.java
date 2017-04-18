package com.cua.admin.model.operation.flight;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "aircraft_insurance")
public class AircraftInsurance implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "aircraft_insurance_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private String type; //Tipo de cobertura
    private String policy; //Número de póliza
    private String company; //Aseguradora
    private LocalDate validityFrom; //Fecha de validez desde
    private LocalDate validityTo; //Fecha de validez hasta

    public Boolean isInForce(LocalDate date) {
        return date.isAfter(validityFrom) && date.isBefore(validityTo);
    }
    
    public Boolean isInForce() {
        return isInForce(LocalDate.now());
    }
    
}
