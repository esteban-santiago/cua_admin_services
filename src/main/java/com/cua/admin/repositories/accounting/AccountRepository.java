/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.AccountingPlan;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findByDescription(String description);
    Account findById(Integer id);
    List<Account> findByFirstOrderGrouper(Integer firstOrder);
}
