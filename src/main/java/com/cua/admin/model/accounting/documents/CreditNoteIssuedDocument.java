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
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "NCE")
public class CreditNoteIssuedDocument extends Document implements Serializable {

    @Column(
            nullable = false,
            unique = true,
            insertable = false,
            updatable = false,
            columnDefinition = "BIGINT DEFAULT nextval('test_seq_1')"
    )
    @Generated(GenerationTime.INSERT)
    private Long creditNoteSequence;

    @Override
    public void setAmount(Float amount) {
        super.setAmount(amount * (-1));
    }

}
