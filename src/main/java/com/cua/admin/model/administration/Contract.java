package com.cua.admin.model.administration;

import com.cua.admin.model.core.Person;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author esantiago
 */
@Getter
@Setter
@Entity
@Table(name = "contract")
//@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Contract implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "contract_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Long id;

    private String description;
    
    
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "person_contract_id_fk"))
    Person person;

    @CreatedDate
    private LocalDateTime creationDate = LocalDateTime.now(); //Fecha de documento
    
    private LocalDate validityFrom;
    
    private LocalDate validityTo;
    
    @Enumerated(EnumType.STRING)
    private Contract.Status status = Contract.Status.ACTIVE;

    private enum Status {
        ACTIVE(),
        INACTIVE(),
    }

    public void activate() {
        this.status = Contract.Status.ACTIVE;
    }

    public Boolean isActive() {
        return this.status == Contract.Status.ACTIVE;
    }

    public void deactivate() {
        this.status = Contract.Status.INACTIVE;
    }

    //public void addAddress(Address address) {
    //    this.addresses.add(address);
    //}

    //public void addContactWay(ContactWay contactWay) {
    //    this.contactWays.add(contactWay);
    //}
}
