/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import lombok.Data;

import java.util.Set;

@Data
public class AccountingPlan {

    private Set<Account> accounts;

}
