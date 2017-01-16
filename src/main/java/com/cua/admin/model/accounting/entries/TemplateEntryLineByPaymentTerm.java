package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.billing.PaymentTerm;
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
@DiscriminatorValue(value = "ENTRY_LINE_PAYMENT_TERM")
public class TemplateEntryLineByPaymentTerm extends TemplateEntryLine {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_term_id", nullable = true)
    private PaymentTerm paymentTerm;
    
}
