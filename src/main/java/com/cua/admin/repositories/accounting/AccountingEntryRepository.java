package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.AccountingEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, Integer> {

    AccountingEntry findById(Integer id);

}
