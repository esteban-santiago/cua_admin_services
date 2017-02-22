package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByDescription(String description);

    Account findById(Integer id);

    List<Account> findByFirstOrderGrouperNot(Integer firstOrder);

    List<Account> findByFirstOrderGrouper(Integer firstOrder);

    List<Account> findBySecondOrderGrouper(Integer secondOrder);
}
