package com.cua.admin.repositories.accounting.entry;

import com.cua.admin.model.accounting.entries.AccountingEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, Integer> {

    AccountingEntry findById(Integer id);

}
