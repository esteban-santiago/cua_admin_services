
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting.documents;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 *
 * @author esteban_santiago
 */

@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "NCE")
public class CreditNoteDocumentType extends DocumentType {
/*
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "document_type_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    private String documentTypeId;
    private String description;
*/
    //@Column(nullable = false, insertable=false, updatable=false,
    //    columnDefinition = "integer DEFAULT nextval('my_sequence')")
    //@Generated(GenerationTime.INSERT) // this is important
    @Column(columnDefinition="serial")
    private Integer sequencedNumber;    

    /**
     * @return the sequencedNumber
     */
    public Integer getSequencedNumber() {
        return sequencedNumber;
    }

    /**
     * @param sequencedNumber the sequencedNumber to set
     */
    public void setSequencedNumber(Integer sequencedNumber) {
        this.sequencedNumber = sequencedNumber;
    }
 
}
