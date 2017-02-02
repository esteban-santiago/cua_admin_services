package com.cua.admin.model.core;

import com.cua.admin.model.core.profiles.Member;
import com.cua.admin.model.core.profiles.Pilot;
import com.cua.admin.model.hr.profiles.Employee;
import com.cua.admin.model.sales.profiles.Customer;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@Table(name = "person")
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "person_id_seq"),
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
    @JoinColumn(name = "nationality_id", nullable = false, foreignKey = @ForeignKey(name = "person_nationality_id_fk"))
    private Nationality nationality;

    private IdentityCard identityCard; //Documento de identidad

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "person_address_id_fk"))
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "person_way_to_contact_id_fk"))
    private Set<ContactWay> contactWays;

    @Enumerated(EnumType.STRING)
    private Person.Status status = Person.Status.ACTIVE;
    
    //Perfiles de la persona
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "person_member_id_fk"))
    private Member memberProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pilot_id", nullable = false, foreignKey = @ForeignKey(name = "person_pilot_id_fk"))
    private Pilot pilotProfile;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "person_customer_id_fk"))
    private Customer customerProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "person_employee_id_fk"))
    private Employee employeeProfile;
    
    private enum Status {
        ACTIVE(),
        INACTIVE(),  
    }
    
    public void activate() {
        this.status = Person.Status.ACTIVE;
    }
 
    public Boolean isActive() {
        return this.status == Person.Status.ACTIVE;
    }
    
    public void deactivate() {
        this.status = Person.Status.INACTIVE;
    }

    public Boolean isInactive() {
        return this.status == Person.Status.INACTIVE;
    }
    
    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void addContactWay(ContactWay contactWay) {
        this.contactWays.add(contactWay);
    }
}
