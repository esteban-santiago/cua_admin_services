/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.Account;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findByDescription(String description);
    Account findById(Integer id);
    List<Account> findByFirstOrderGrouperNot(Integer firstOrder);
    List<Account> findByFirstOrderGrouper(Integer firstOrder);
    List<Account> findBySecondOrderGrouper(Integer firstOrder);
}
