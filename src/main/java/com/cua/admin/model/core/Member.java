package com.cua.admin.model.core;

import com.cua.admin.model.operation.flight.PilotRating;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Member implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "member_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private String name;

    //@JsonFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    private LocalDate dateOfCreation = LocalDate.now(); //Fecha de Alta

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth; //Fecha de nacimiento
    
    @OneToOne
    @JoinColumn(name = "nationality_id", nullable = false, foreignKey = @ForeignKey(name = "nationality_member_id_fk"))
    private Nationality nationality;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "category_member_id_fk"))
    private Category category;

    private IdentityCard identityCard; //Documento de identidad

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "address_member_id_fk"))
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "way_to_contact_member_id_fk"))
    private Set<ContactWay> contactWays;

    @ElementCollection
    @JoinTable(
            name="member_rating_type", // ref table.
            joinColumns = {@JoinColumn(name="member_id")}
    )
    @Column(name="rating_id", nullable = false)
    private Set<PilotRating> ratings;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ElementCollection
    @JoinTable(
            name="member_role_type", // ref table.
            joinColumns = {@JoinColumn(name="member_id")}
    )
    @Column(name="rol_id", nullable = false)
    private Set<MemberRole> roles;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "medical_certification_member_id_fk"))
    private Set<MedicalCertification> medicalCertifications;
    
    public void activate() {
        this.status = Status.ACTIVE;
    }
 
    public Boolean isActive() {
        return this.status == Status.ACTIVE;
    }
    
    public void deactivate() {
        this.status = Status.INACTIVE;
    }

    public Boolean isInactive() {
        return this.status == Status.INACTIVE;
    }
   
    public void dismiss() {
        this.status = Status.DISMISS;
    }

    public Boolean isDismiss() {
        return this.status == Status.DISMISS;
    }

    
    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void addContactWay(ContactWay contactWay) {
        this.contactWays.add(contactWay);
    }
    
    public void addRating(PilotRating rating) {
        this.ratings.add(rating);
    }

    public void addRole(MemberRole role) {
        this.roles.add(role);
    }

    public void addMedicalCertification(MedicalCertification medicalCertification) {
        this.medicalCertifications.add(medicalCertification);
    }
    
    private enum Status {
        ACTIVE(),
        INACTIVE(),
        DISMISS();    
    }
    
}