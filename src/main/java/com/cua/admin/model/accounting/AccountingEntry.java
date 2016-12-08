/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import com.cua.admin.model.core.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "accounting_entry_header")
public class AccountingEntry implements Serializable {
    @Id
    private Integer id;
    private String description; //Descripción del asiento
    private LocalDate creationDate;
    private Integer fiscalYear;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "accounting_document_id", foreignKey = @ForeignKey(name = "accounting_entry_id_fk"))
    private Set<AccountingEntryItem> entryItems;    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "accounting_entry_user_id_fk"))
    private User user;


    public AccountingEntry() {
        
    }
    
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
     * @return the entryItems
     */
    public Set<AccountingEntryItem> getEntryItems() {
        return entryItems;
    }

    /**
     * @param entryItems the entryItems to set
     */
    public void setEntryItems(Set<AccountingEntryItem> entryItems) {
        this.entryItems = entryItems;
    }
    
    /**
     * @param entryItem
     */
    public void addEntryItem(AccountingEntryItem entryItem) {
        this.entryItems.add(entryItem);
    }
}
