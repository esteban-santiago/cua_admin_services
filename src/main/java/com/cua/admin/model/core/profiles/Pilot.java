/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.core.profiles;

import com.cua.admin.model.operation.flight.PilotRating;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pilot")
public class Pilot implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "pilot_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    
    private String license;
    
    @ElementCollection
    @JoinTable(
            name="pilot_rating_type", // ref table.
            joinColumns = {@JoinColumn(name="pilot_id")}
    )
    @Column(name="rating_id", nullable = false)
    private Set<PilotRating> ratings;
    
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pilot_id", nullable = false, foreignKey = @ForeignKey(name = "pilot_medical_certification_id_fk"))
    private Set<MedicalCertification> medicalCertifications;

    @ElementCollection
    @JoinTable(
            name="pilot_role_type", // ref table.
            joinColumns = {@JoinColumn(name="pilot_id")}
    )
    @Column(name="rol_id", nullable = false)
    private Set<PilotRole> roles;    
    
    
    public void addRating(PilotRating rating) {
        this.ratings.add(rating);
    }
    
    public void addMedicalCertification(MedicalCertification medicalCertification) {
        this.medicalCertifications.add(medicalCertification);
    }
     
    public void addRole(PilotRole role) {
        this.roles.add(role);
    }
}
