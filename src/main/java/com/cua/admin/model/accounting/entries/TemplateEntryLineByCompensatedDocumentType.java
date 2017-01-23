package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.finance.Document;
import com.cua.admin.model.finance.DocumentType;
import java.io.Serializable;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author esteban_santiago
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "ENTRY_LINE_COMPENSATED_DOC_TYPE")
public class TemplateEntryLineByCompensatedDocumentType extends TemplateEntryLine implements Serializable {
    
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

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
    @Enumerated(EnumType.STRING)
    private AccountingEntryItemType accountingEntryItemType;
    
    @Override
    public Boolean match(Document document) {
        return this.documentType == document.getCompensationDocument().getDocumentType();
    }
}
