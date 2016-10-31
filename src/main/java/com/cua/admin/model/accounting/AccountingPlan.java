/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import java.util.Set;

/**
 *
 * @author esteban_santiago
 */
public class AccountingPlan {
    private Set<Account> accounts;

    /**
     * @return the accounts
     */
    public Set<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
