/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories.accounting;

import com.cua.admin.model.accounting.Currency;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    List<Currency> findByDescription(String description);

    Currency findById(Integer id);
}
