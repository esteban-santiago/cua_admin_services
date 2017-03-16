package com.cua.admin.model.commercial;

import javax.persistence.Column;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author esantiago
 */
public class DebitNote {
    @Column(
        nullable = false,
        unique = true,
        columnDefinition = "BIGINT DEFAULT nextval('debit_note_id_seq')"
    )
    @Generated(GenerationTime.INSERT)
    private Long id;   
}
