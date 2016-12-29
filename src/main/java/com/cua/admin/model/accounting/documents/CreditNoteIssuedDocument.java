package com.cua.admin.model.accounting.documents;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "NCE")
public class CreditNoteIssuedDocument extends Document implements Serializable {

    @Column(
            name = "legalId",
            nullable = false,
            unique = false,
            insertable = false,
            updatable = false,
            columnDefinition = "BIGINT DEFAULT nextval('document_credit_note_issued_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    
    private Long legalId;
    
    @Override
    public void setAmount(Float amount) {
        super.setAmount(amount * (-1));
    }

}
