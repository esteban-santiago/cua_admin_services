package com.cua.admin.model.finance.billing;

import com.cua.admin.model.finance.Currency;
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
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esantiago
 */
@Data
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "payment_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "payment_method_id") //, foreignKey = @ForeignKey(name = "payment_method_id_fk"))
    private PaymentMethod method;

    @OneToOne
    @JoinColumn(name = "payment_term_id", foreignKey = @ForeignKey(name = "payment_term_id_fk"))
    private PaymentTerm term;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Float amount = 0F;
    
    private Float charge = 0F;
    
    private Float discount = 0F;
    
    private String description;
    
    public Float getTotalAmount() {
        return (amount + charge - discount);
    }

}
