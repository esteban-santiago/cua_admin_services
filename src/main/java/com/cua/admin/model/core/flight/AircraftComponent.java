package com.cua.admin.model.core.flight;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@Table(name = "aircraft_component")
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
    private Long id;

    private String description;

    private String serial;    
    
    @OneToOne    
    @JoinColumn(name="component_type_id")
    private AircraftComponentType type;
}
