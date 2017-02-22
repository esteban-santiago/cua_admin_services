package com.cua.admin.model.operation.flight;

import com.cua.admin.model.operation.flight.profiles.ProductProfile;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "aircraft", uniqueConstraints = {@UniqueConstraint(columnNames = {"registration"})})
public class Aircraft implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "aircraft_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private String registration; //Matrícula
    private String model; //Modelo
    
    @Enumerated(EnumType.STRING)
    private Status status; //Status: Activo, Inactivo
    
    private String brand; //Marca

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id", nullable = false, foreignKey = @ForeignKey(name = "aircraft_insurance_id_fk"))
    private Set<AircraftInsurance> insurances;

    @OneToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "aircraft_id", nullable = false, foreignKey = @ForeignKey(name = "aircraft_component_id_fk"))
    private Set<AircraftComponent> components;
    
    //Profile para pricing
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_profile_id", foreignKey = @ForeignKey(name = "product_profile_id_fk"))
    private ProductProfile productProfile;
    
    public Boolean hasAnInsurancePolicyInForce() {
        return hasAnInsurancePolicyInForce(LocalDate.now());
    }
    
    public Boolean hasAnInsurancePolicyInForce(LocalDate date) {
        return getInsurances().stream().anyMatch(_insurance -> _insurance.isInForce(date));
    }
    
    public enum Status {
        ACTIVE(),
        INACTIVE(),
        MAINTENANCE(),
        OUT_OF_ORDER();
    }
}
