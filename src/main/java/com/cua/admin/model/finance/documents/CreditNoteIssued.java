package com.cua.admin.model.finance.documents;

import com.cua.admin.model.finance.DocumentType;
import com.cua.admin.model.finance.Document;
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
@EqualsAndHashCode(callSuper = true)
@Entity
public class CreditNoteIssued extends Document implements Serializable {

    @Column(
            nullable = false,
            unique = true,
            insertable = true,
            updatable = true,
            columnDefinition = "BIGINT DEFAULT nextval('document_credit_note_issued_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    
    private Long legalId;
    
    @Override
    public void setAmount(Float amount) {
        super.setAmount(amount * (-1));
    }

    public CreditNoteIssued() {
        setDocumentType(DocumentType.CNI);  
    }
}