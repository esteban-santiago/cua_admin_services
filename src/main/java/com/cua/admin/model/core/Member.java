package com.cua.admin.model.core;

import com.cua.admin.model.flight.PilotRating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    @CreatedDate
    private LocalDate dateOfCreation = LocalDate.now(); //Fecha de Alta

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private LocalDate dateOfBirth; //Fecha de nacimiento
    
    @OneToOne
    @JoinColumn(name = "nationality_id", foreignKey = @ForeignKey(name = "member_nationality_id_fk"))
    private Nationality nationality = new Nationality(); //Nacionalidad

    private IdentityCard identityCard; //Documento de identidad

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "member_address_id_fk"))
    private Set<Address> addresses = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "member_category_id_fk"))
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "member_way_to_contact_id_fk"))
    private Set<ContactWay> contactWays;

    @ElementCollection
    @JoinTable(
            name="member_rating_type", // ref table.
            joinColumns = {@JoinColumn(name="member_id")}
    )
    @Column(name="rating_id", nullable = false)
    private Set<PilotRating> ratings = new HashSet<>();
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ElementCollection
    @JoinTable(
            name="member_role_type", // ref table.
            joinColumns = {@JoinColumn(name="member_id")}
    )
    @Column(name="rol_id", nullable = false)
    private Set<MemberRole> roles;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "member_medical_certification_id_fk"))
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