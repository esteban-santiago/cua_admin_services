package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.billing.PaymentMethod;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author esantiago
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "ENTRY_LINE_PAYMENT_METHOD")
public class TemplateEntryLineByPaymentMethod extends TemplateEntryLine {
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id", nullable = true)
    private PaymentMethod paymentMethod;

    
}
