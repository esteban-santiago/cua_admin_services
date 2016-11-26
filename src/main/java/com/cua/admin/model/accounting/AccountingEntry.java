/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author esteban_santiago
 */
public class AccountingEntry implements Serializable {
    private Set<AccountingEntryLine> accountingEntryLine;

    /**
     * @return the accountingEntryLine
     */
    public Set<AccountingEntryLine> getAccountingEntryLine() {
        return accountingEntryLine;
    }

    /**
     * @param accountingEntryLine the accountingEntryLine to set
     */
    public void setAccountingEntryLine(Set<AccountingEntryLine> accountingEntryLine) {
        this.accountingEntryLine = accountingEntryLine;
    }

    /**
     * @param accountingEntryLine the accountingEntryLine to set
     */
    public void addAccountingEntryLine(AccountingEntryLine accountingEntryLine) {
        this.accountingEntryLine.add(accountingEntryLine);
    }  
}
