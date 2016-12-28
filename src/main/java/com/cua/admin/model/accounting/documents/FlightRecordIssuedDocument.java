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
@DiscriminatorValue(value = "FVE")
public class FlightRecordIssuedDocument extends Document implements Serializable {

    @Column(
            nullable = false,
            unique = true,
            insertable = false,
            updatable = false,
            columnDefinition = "BIGINT DEFAULT nextval('document_flight_record_issued_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    private Long flightRecordSequence;
}