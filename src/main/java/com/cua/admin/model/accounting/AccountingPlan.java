package com.cua.admin.model.accounting;

import lombok.Data;

import java.util.Set;

@Data
public class AccountingPlan {

    private Set<Account> accounts;

}
