package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.accounting.*;
import com.cua.admin.model.accounting.documents.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author esteban_santiago
 */
@Data
@Table(name="template_entry")
@Entity
public class TemplateEntry implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "template_entry_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id; //Número de asiento modelo
        
    private String description;
    
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "template_entry_id", foreignKey = @ForeignKey(name = "template_entry_id_fk"))
    private Set<TemplateEntryLine> entryLines = new HashSet<>(); 

    @Transient
    private AccountingEntry entry = new AccountingEntry();    
    
    public void addEntryLine(TemplateEntryLine entryLine) {
        this.entryLines.add(entryLine);
    }
    
    private Set<TemplateEntryLine> getCreditEntryLines() {
        return entryLines.stream().filter(entryLine -> entryLine.
                getAccountingEntryItemType().equals(AccountingEntryItemType.CREDIT)).
                collect(Collectors.toSet());
    }

    private Set<TemplateEntryLine> getDebitEntryLines() {
        return entryLines.stream().filter(entryLine -> entryLine.
                getAccountingEntryItemType().equals(AccountingEntryItemType.DEBIT)).
                collect(Collectors.toSet());
    }

    public AccountingEntry getAccountingEntry(Document document) {
        entry.setCreationDate(LocalDateTime.now());
        entry.setDescription(this.description);
        entry.setFiscalYear(LocalDate.now().getYear());
        
        getCreditEntryLines().stream().forEach(entryLine -> {
            AccountingEntryItem item = new AccountingEntryItem();
            item.setAccount(entryLine.getAccount());
            item.setItemType(AccountingEntryItemType.CREDIT);
            item.setAmount(entryLine.getFactor()* document.getAmount());
            item.setCurrency(document.getCurrency());
            entry.addEntryItem(item);
        });
        getDebitEntryLines().stream().forEach(entryLine -> {
            AccountingEntryItem item = new AccountingEntryItem();
            item.setAccount(entryLine.getAccount());
            item.setItemType(AccountingEntryItemType.DEBIT);
            item.setAmount(entryLine.getFactor()* document.getAmount());
            item.setCurrency(document.getCurrency());
            entry.addEntryItem(item);
        });
        return this.entry;
    }
    
}
