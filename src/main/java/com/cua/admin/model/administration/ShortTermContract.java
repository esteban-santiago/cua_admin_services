package com.cua.admin.model.administration;

import com.cua.admin.model.core.Person;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esantiago
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "short_term_contract")
public class ShortTermContract implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "short_contract_id_seq")
                ,
                @Parameter(name = "initial_value", value = "1")
                ,
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private String name;
    
    
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "person_short_term_contract_id_fk"))
    Person person;

    @Enumerated(EnumType.STRING)
    private ShortTermContract.Status status = ShortTermContract.Status.ACTIVE;

    private enum Status {
        ACTIVE(),
        INACTIVE(),
    }

    public void activate() {
        this.status = ShortTermContract.Status.ACTIVE;
    }

    public Boolean isActive() {
        return this.status == ShortTermContract.Status.ACTIVE;
    }

    public void deactivate() {
        this.status = ShortTermContract.Status.INACTIVE;
    }

    //public void addAddress(Address address) {
    //    this.addresses.add(address);
    //}

    //public void addContactWay(ContactWay contactWay) {
    //    this.contactWays.add(contactWay);
    //}
}
