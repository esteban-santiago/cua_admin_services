/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.entities.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author esteban_santiago
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByDescription(String description);

    Category findById(Integer id);
}
