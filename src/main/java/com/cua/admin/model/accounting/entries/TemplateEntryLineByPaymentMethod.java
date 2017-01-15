package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.billing.PaymentTerm;
import javax.persistence.*;

/**
 *
 * @author esantiago
 */
public class TemplateEntryLineByPaymentMethod extends TemplateEntryLine {
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_term_id", nullable = false)
    private PaymentTerm paymentTerm;
  
}
