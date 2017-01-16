package com.cua.admin.model.accounting;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Lineas del asiento
 */
@Data
@Entity
@Table(name = "accounting_entry_item")
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

    @Enumerated(EnumType.STRING)
    private AccountingEntryItemType itemType;
     
    private Float amount;
    
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "accounting_entry_item_account_id_fk"))
    private Account account;

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
