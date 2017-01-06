package com.cua.admin.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "payment_type_del", discriminatorType = DiscriminatorType.STRING)
@Table(name = "payment_method")
public class PaymentMethod implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "payment_method_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    private String description;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private Float discount;
    private Float charge;
    private Float fee;
    
    public PaymentMethod(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
