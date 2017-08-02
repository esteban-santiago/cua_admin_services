package com.cua.admin.model.administration.membership;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.operation.inventory.ProductProfile;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "membership_fee")
@RequiredArgsConstructor
public class MembershipFee implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "membership_fee_id_seq")
                ,
                @Parameter(name = "initial_value", value = "1")
                ,
                @Parameter(name = "increment_size", value = "1")
            }
    )

    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "person_membership_fee_id_fk"))
    Person person;

    Integer month;

    Integer year;

    //Profile para pricing
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_profile_id", foreignKey = @ForeignKey(name = "membership_fee_product_profile_id_fk"))
    private ProductProfile productProfile;

    @Enumerated(EnumType.STRING)
    private Status status = Status.OPENED;

    public void open() {
        this.status = Status.OPENED;
    }

    public void close() {
        this.status = Status.CLOSED;
    }

    public void cancel() {
        this.status = Status.CANCELED;
    }

    public Boolean isOpened() {
        return this.status.equals(Status.OPENED);
    }

    public Boolean isClosed() {
        return this.status.equals(Status.CLOSED);
    }

    public Boolean isCanceled() {
        return this.status.equals(Status.CANCELED);
    }

    public enum Status {
        OPENED(),
        CLOSED(),
        CANCELED();
    }

}
