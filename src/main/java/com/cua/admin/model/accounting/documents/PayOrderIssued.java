package com.cua.admin.model.accounting.documents;

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
public class PayOrderIssued extends Document implements Serializable {

    @Column(
            nullable = false,
            unique = true,
            insertable = true,
            updatable = true,
            columnDefinition = "BIGINT DEFAULT nextval('document_pay_order_issued_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    private Long legalId;

    public PayOrderIssued() {
        setDocumentType(DocumentType.POI);  
    }
}