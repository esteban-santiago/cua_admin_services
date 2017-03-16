package com.cua.admin.model.commercial;

import javax.persistence.Column;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author esantiago
 */
public class Receipt extends Document {
    @Column(
        nullable = false,
        unique = true,
        columnDefinition = "BIGINT DEFAULT nextval('credit_note_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    private Long id;

}
