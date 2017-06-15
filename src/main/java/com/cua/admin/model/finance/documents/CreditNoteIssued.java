package com.cua.admin.model.finance.documents;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
public class CreditNoteIssued extends Document implements Serializable {

    public CreditNoteIssued() {
        setDocumentType(DocumentType.CNI);
    }

    @Column(
            nullable = false,
            unique = true,
            columnDefinition = "BIGINT DEFAULT nextval('document_credit_note_issued_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    private Long legalId;

    @Override
    public Float getAmount() {
        return super.getAmount() * (-1);
    }

    @Override
    public Float getTotalAmount() {
        return super.getTotalAmount() * (-1);
    }

    @Override
    public Float getCharge() {
        return super.getCharge() * (-1);
    }

    @Override
    public Float getDiscount() {
        return super.getDiscount() * (-1);
    }
}
