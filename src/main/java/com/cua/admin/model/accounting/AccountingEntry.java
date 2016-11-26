/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import com.cua.admin.model.accounting.documents.CreditNoteDocumentType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author esteban_santiago
 */
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "accounting_entry")
public class AccountingEntry implements Serializable {

    public AccountingEntry() {
    }
    @Id
    private Integer id;
    private LocalDate creationDate;
    private LocalTime creationTime;
    private String debit;
    private String credit;
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

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the creationDate
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the creationTime
     */
    public LocalTime getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(LocalTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return the debit
     */
    public String getDebit() {
        return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(String debit) {
        this.debit = debit;
    }

    /**
     * @return the credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    
}
