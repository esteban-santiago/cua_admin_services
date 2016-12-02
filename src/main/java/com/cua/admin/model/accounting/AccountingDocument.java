/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import com.cua.admin.model.accounting.items.AccountingItem;
import com.cua.admin.model.core.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * Cabecera del asiento
 * @author esteban_santiago
 * 
 */

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "accounting_document")
public class AccountingDocument implements Serializable {
    @Id
    private Integer id;
    private String description; //Descripción del asiento
    private LocalDate creationDate;
    private Integer fiscalYear;
    private AccountingItem accountingItem;
    private User user;


    private Set<AccountingEntry> entry;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the fiscalYear
     */
    public Integer getFiscalYear() {
        return fiscalYear;
    }

    /**
     * @param fiscalYear the fiscalYear to set
     */
    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * @return the accountingItem
     */
    public AccountingItem getAccountingItem() {
        return accountingItem;
    }

    /**
     * @param accountingItem the accountingItem to set
     */
    public void setAccountingItem(AccountingItem accountingItem) {
        this.accountingItem = accountingItem;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the entry
     */
    public Set<AccountingEntry> getEntry() {
        return entry;
    }

    /**
     * @param entry the entry to set
     */
    public void setEntry(Set<AccountingEntry> entry) {
        this.entry = entry;
    }
    
}
