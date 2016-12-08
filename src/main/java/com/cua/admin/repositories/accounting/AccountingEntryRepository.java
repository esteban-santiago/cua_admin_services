/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.AccountingEntry;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface AccountingEntryRepository extends CrudRepository<AccountingEntry, Integer> {

    AccountingEntry findById(Integer id);

}
