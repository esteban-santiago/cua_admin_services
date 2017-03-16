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
@EqualsAndHashCode(callSuper = true)
@Entity
public class FlightRecordIssued extends Document implements Serializable {
    @Column(
        nullable = false,
        unique = true,
        columnDefinition = "BIGINT DEFAULT nextval('document_flight_record_issued_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    private Long legalId;
    
    public FlightRecordIssued() {
        setDocumentType(DocumentType.FRI);
    }
}