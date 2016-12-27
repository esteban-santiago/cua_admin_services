package com.cua.admin.model.accounting.documents;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Override
    public void setAmount(Float amount) {
        super.setAmount(amount * (-1));
    }

}
