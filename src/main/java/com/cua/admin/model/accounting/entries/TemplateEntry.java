/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.accounting.AccountingEntry;
import com.cua.admin.model.accounting.AccountingEntryItem;
import com.cua.admin.model.accounting.documents.Document;
import com.cua.admin.model.accounting.documents.DocumentType;
import com.cua.admin.model.billing.PaymentType;
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
    
    @Transient
    private AccountingEntry entry = new AccountingEntry();
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "template_entry_id", foreignKey = @ForeignKey(name = "template_entry_id_fk"))
    private Set <TemplateEntryLine>entryLines = new HashSet<>(); 
    public void addEntryLine(TemplateEntryLine entryLine) {
        this.entryLines.add(entryLine);
    }
    
    private Set<TemplateEntryLine> getCreditEntryLines() {
        return entryLines.stream().filter(entryLine -> entryLine.getCredit()!= 0).
                map(entryLine -> entryLine
                    ).collect(Collectors.toCollection(HashSet::new));
    }

    private Set<TemplateEntryLine> getDebitEntryLines() {
        return entryLines.stream().filter(entryLine -> entryLine.getDebit()!= 0).
                map(entryLine -> entryLine
                    ).collect(Collectors.toCollection(HashSet::new));
    }

    public Set<TemplateEntryLine> getEntryLineByPaymentType(PaymentType paymentType) {
        //entryLines.stream().filter(!entryLine -> entryLine.getPaymentType().equals(null))
        return entryLines.stream().
                filter(entryLine -> entryLine.getPaymentType() != null).
                filter(entryLine -> entryLine.getPaymentType().equals(paymentType)).
                map(entryLine->entryLine).
                collect(Collectors.toCollection(HashSet::new));
    }

    public AccountingEntry getAccountingEntry(Document document) {
        entry.setCreationDate(LocalDateTime.now());
        entry.setDescription(this.description);
        entry.setFiscalYear(LocalDate.now().getYear());
        entryLines.stream().forEach(entryLine -> {
            AccountingEntryItem item = new AccountingEntryItem();
            item.setAccount(entryLine.getAccount());
            item.setCredit(entryLine.getCredit() * document.getAmount());
            item.setDebit(entryLine.getDebit() * document.getAmount());
            entry.addEntryItem(item);
        });
        return this.entry;
    }
    
}
