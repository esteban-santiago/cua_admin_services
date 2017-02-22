package com.cua.admin.model.finance.documents;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class FlightRecordIssued extends Document implements Serializable {

    @Column(
            nullable = false,
            unique = true,
            insertable = true,
            updatable = true,
            columnDefinition = "BIGINT DEFAULT nextval('document_flight_record_issued_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    private Long legalId;

    public FlightRecordIssued() {
        setDocumentType(DocumentType.FRI);  
    }
}