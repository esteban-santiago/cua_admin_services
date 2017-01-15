package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.billing.PaymentMethod;
import javax.persistence.*;

/**
 *
 * @author esantiago
 */
public class TemplateEntryLineByPaymentTerm extends TemplateEntryLine {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;
  
}
