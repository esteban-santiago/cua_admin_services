package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.AccountingEntry;
import org.springframework.data.repository.CrudRepository;

public interface AccountingEntryRepository extends CrudRepository<AccountingEntry, Integer> {

    AccountingEntry findById(Integer id);

}
