package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findByDescription(String description);

    Account findById(Integer id);

    List<Account> findByFirstOrderGrouperNot(Integer firstOrder);

    List<Account> findByFirstOrderGrouper(Integer firstOrder);

    List<Account> findBySecondOrderGrouper(Integer secondOrder);
}
