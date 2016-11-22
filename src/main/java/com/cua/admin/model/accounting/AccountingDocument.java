/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 *
 * @author esteban_santiago
 */

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "accounting_document")
public class AccountingDocument implements Serializable {
    @Id
    private Integer id;
    //private Set<AccountingEntry> accountingEntry;
    
}
