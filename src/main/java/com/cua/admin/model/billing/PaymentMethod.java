package com.cua.admin.model.billing;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



/**
 *
 * @author esantiago
 */

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "payment_method")
public class PaymentMethod implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "payment_type_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    
    private String description;
    
   @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method__id", nullable = false, foreignKey = @ForeignKey(name = "payment_method_term_id_fk"))
    Set <PaymentTerm> paymentTerm;
}
