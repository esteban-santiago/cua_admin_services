package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.billing.PaymentMethod;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author esantiago
 */
public class TemplateEntryLineByPaymentMethod extends TemplateEntryLine {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;
  
}
