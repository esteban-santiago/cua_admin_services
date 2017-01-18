package com.cua.admin.model.core.flight;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "airfield")
@SuppressWarnings("ValidAttributes")
public class Airfield implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "airfield_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    @Column(name = "iata_code")
    private String IATACode;

    @Column(name = "OACI_code")
    private String OACICode;

    private String name;
    private String latitude;
    private String longitude;
    
    @Enumerated(EnumType.STRING)
    private Size size;

    public enum Size {
        SMALL(),
        MEDIUM(),
        LARGE();
    }
    
}
