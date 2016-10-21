/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.Person;
import com.cua.admin.model.PersonCategory;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface PersonCategoryRepository extends CrudRepository<PersonCategory, Integer> {

    List<PersonCategory> findByDescription(String description);
    PersonCategory findById(Integer id);
}
