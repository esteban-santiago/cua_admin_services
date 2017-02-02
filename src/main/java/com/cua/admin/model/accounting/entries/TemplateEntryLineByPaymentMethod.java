package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.billing.PaymentMethod;
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
    
    @OneToOne
    @JoinColumn(name = "payment_method_id", nullable = true)
    private PaymentMethod paymentMethod;

    @Override
    public Boolean match(Document document) {
        return this.paymentMethod.equals(document.getPaymentMethod());
    }    
}
