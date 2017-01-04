package com.cua.admin.model.core;

import com.cua.admin.model.core.flight.Rating;
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
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private String name;

    @CreatedDate
    private LocalDate dateOfCreation = LocalDate.now(); //Fecha de Alta

    private LocalDate dateOfBirth; //Fecha de nacimiento

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "nationality_id", foreignKey = @ForeignKey(name = "member_nationality_id_fk"))
    private Nationality nationality; //Nacionalidad

    private IdentityCard identityCard; //Documento de identidad

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinTable(name = "member_address")
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "member_address_id_fk"))
    private Set<Address> address = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "member_category_id_fk"))
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "member_way_to_contact_id_fk"))
    private Set<ContactWay> contactWay = new HashSet<>();

    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(
            name="member_rating_type", // ref table.
            joinColumns = {@JoinColumn(name="member_id")}
    )
    @Column(name="rating_id", nullable = false)
    private Set<Rating> ratings = new HashSet<>();
    
    @Enumerated(EnumType.STRING)
    private MemberStatus status = MemberStatus.ACTIVE;

    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(
            name="member_role_type", // ref table.
            joinColumns = {@JoinColumn(name="member_id")}
    )
    @Column(name="rol_id", nullable = false)
    private Set<MemberRole> roles = new HashSet<>();

    
    public void addAddress(Address address) {
        this.address.add(address);
    }

    public void addWayToContact(ContactWay contactWay) {
        this.contactWay.add(contactWay);
    }

}
