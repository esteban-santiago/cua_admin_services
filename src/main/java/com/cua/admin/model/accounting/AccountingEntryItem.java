package com.cua.admin.model.accounting;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Lineas del asiento
 */
@Data
@Entity
@Table(name = "accounting_entry_item")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class AccountingEntryItem implements Serializable {
     @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "accounting_entry_item_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private Float debit;
    private Float credit;

    @CreatedDate
    private LocalDateTime creationDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "accounting_entry_item_account_id_fk"))
    private Account account;

    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    //@JoinColumn(name = "document_type_id", foreignKey = @ForeignKey(name = "accounting_entry_document_type_id_fk"))
    //private CreditNoteDocumentType documentType;

/*    
Año fiscal
Periodo Fiscal
Descripción Asiento
Debe
Haber
Tipo de documento
Número de documento
Moneda del documento
Importe en moneda del documento
Moneda local
Importe en moneda local
Cuenta contable
*/

}
