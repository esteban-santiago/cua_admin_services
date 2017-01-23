package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.finance.Document;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ENTRY_LINE_DISCRIMINATOR")
@DiscriminatorValue(value = "ENTRY_LINE_BASE")
@Table(name="template_entry_line")
public class TemplateEntryLine implements Serializable {
    
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "template_entry_line_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id; //Número de linea de asiento

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
    @Enumerated(EnumType.STRING)
    private AccountingEntryItemType accountingEntryItemType;
            
    private Float factor;
    
    public Boolean match(Document document) {
        return true;
    }
}
