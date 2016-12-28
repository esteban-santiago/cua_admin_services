package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    List<Currency> findByDescription(String description);

    Currency findById(Integer id);
}
