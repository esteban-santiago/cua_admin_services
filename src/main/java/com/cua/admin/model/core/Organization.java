package com.cua.admin.model.core;

import java.io.Serializable;
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
//@Entity
//@Table(name = "organization")
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@AllArgsConstructor
public class Organization implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "organization_id_seq")
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

    private String taxId; //CUIT

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", nullable = false, foreignKey = @ForeignKey(name = "organization_address_id_fk"))
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", nullable = false, foreignKey = @ForeignKey(name = "organization_way_to_contact_id_fk"))
    private Set<ContactWay> contactWays;

    @Enumerated(EnumType.STRING)
    private Organization.Status status = Organization.Status.ACTIVE;

    private enum Status {
        ACTIVE(),
        INACTIVE(),
    }

    public void activate() {
        this.status = Organization.Status.ACTIVE;
    }

    public Boolean isActive() {
        return this.status == Organization.Status.ACTIVE;
    }

    public void deactivate() {
        this.status = Organization.Status.INACTIVE;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void addContactWay(ContactWay contactWay) {
        this.contactWays.add(contactWay);
    }
}
